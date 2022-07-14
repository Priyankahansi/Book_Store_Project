package com.bridgelabz.service;

import com.bridgelabz.DTO.BookDetailsDTO;
import com.bridgelabz.DTO.ResponseDTO;
import com.bridgelabz.Exception.UserException;
import com.bridgelabz.Model.BookDetails;
import com.bridgelabz.Model.UserModel;
import com.bridgelabz.Repository.IBookDetailsRepository;
import com.bridgelabz.Util.EmailSenderService;
import com.bridgelabz.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailsService implements IBookDetailsService {

    @Autowired
    IBookDetailsRepository repo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService sender;

    @Override
    public String getMessage() {
        return "welcome to Book Store";
    }
    @Override
    public BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO) {
    BookDetails bookDetails=new BookDetails(bookDetailsDTO);
    repo.save(bookDetails);
    sender.sendEmail("priyankahansi95@gmail.com",
            "Test Mail","Book Added Successfully"+bookDetails.toString());
    return bookDetails;
    }

    @Override
    public Optional<BookDetails> getBookById(int getBookId) {
        Optional<BookDetails> book = repo.findById(getBookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new UserException("Book not found");
        }
    }

    @Override
    public List<BookDetails> getListOfBooks() {
        List<BookDetails> books = repo.findAll();
        return books;
    }

    @Override
    public void deleteBook(int bookId) {
        Optional<BookDetails> bookDetails = repo.findById(bookId);
        repo.deleteById(bookId);
        sender.sendEmail("priyankahansi95@gmail.com", "Test Mail-BookStore",
                "Book deleted Successfully" + bookDetails.toString());
    }

    @Override
    public BookDetails updateBookById(int getBookId, BookDetailsDTO bookDetailsDTO) {
        Optional<BookDetails> newBook = repo.findById(getBookId);
        if(newBook.isPresent()){
            newBook.get().setBookName(bookDetailsDTO.getBookName());
            newBook.get().setBookDescription(bookDetailsDTO.getBookDescription());
            newBook.get().setBookImg(bookDetailsDTO.getBookImg());
            newBook.get().setAuthorName(bookDetailsDTO.getAuthorName());
            newBook.get().setPrice(bookDetailsDTO.getPrice());
            repo.save(newBook.get());
            sender.sendEmail("priyankahansi@gmail.com", "Test Mail-BookStore",
                    "To get Updated Book: click here->" +
                            "http://localhost:8080/book/getBook/"+getBookId);
            return newBook.get();
        }
        else{
            throw new UserException("Book not found");
        }
    }

    @Override
    public BookDetails updateBookQuantity(int getBookId, int quantity) {
        Optional<BookDetails> newBook = repo.findById(getBookId);
        if(newBook.isPresent()){
            newBook.get().setQuantity(quantity);
            repo.save(newBook.get());
            return newBook.get();
        }
        else{
            throw new UserException("Book not found");
        }
    }
    @Override
    public List<BookDetails> getListOfBooksInSortedDsc() {
        List<BookDetails> bookDetails=repo.findAll(Sort.by("bookName").descending());
        return bookDetails;
    }
    @Override
    public List<BookDetails> getListOfBooksInSortedAce() {
      List<BookDetails> bookDetails=repo.findAll(Sort.by("bookName").ascending());
      return bookDetails;
    }

    @Override
    public List<BookDetails> getBookByName(String bookName) {
        return repo.findBookByName(bookName);
    }
}
