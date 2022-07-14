package com.bridgelabz.service;

import com.bridgelabz.DTO.BookDetailsDTO;
import com.bridgelabz.Model.BookDetails;
import com.bridgelabz.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IBookDetailsService {
    String getMessage();

    BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO);

    Optional<BookDetails> getBookById(int getBookId);

    List<BookDetails> getListOfBooks();

    void deleteBook(int bookId);

    BookDetails updateBookById(int getBookId, BookDetailsDTO bookDetailsDTO);

    BookDetails updateBookQuantity(int getBookId, int quantity);
    List<BookDetails> getListOfBooksInSortedDsc();

    List<BookDetails> getListOfBooksInSortedAce();


    List<BookDetails> getBookByName(String bookName);

}
