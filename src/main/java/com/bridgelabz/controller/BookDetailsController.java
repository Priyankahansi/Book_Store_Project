package com.bridgelabz.controller;

import com.bridgelabz.DTO.BookDetailsDTO;
import com.bridgelabz.DTO.ResponseDTO;
import com.bridgelabz.Model.BookDetails;
import com.bridgelabz.service.IBookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookDetailsController {

    @Autowired
    IBookDetailsService service;

    @GetMapping("/home")
    public String hello() {
        String msg = service.getMessage();
        return msg;
    }
    @PostMapping(value = "/addBook")
    public ResponseEntity<ResponseDTO> addBookData(@Valid @RequestBody BookDetailsDTO bookDetailsDTO)
    {
        BookDetails addBookDetails = service.addBookDetails(bookDetailsDTO);
        ResponseDTO response = new ResponseDTO("Book added successfully",addBookDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getBook/{getBookId}")
    public ResponseEntity<ResponseDTO> getBookId(@PathVariable int getBookId) {
        Optional<BookDetails> book = service.getBookById(getBookId);
        ResponseDTO response = new ResponseDTO("Book retrieved successfully by id:" +" " +getBookId,book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getBooks")
    public ResponseEntity<ResponseDTO> getBooks() {
        List<BookDetails> books = service.getListOfBooks();
        ResponseDTO response = new ResponseDTO("Retrieved list of Books Successfully", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/deleteBook")
    public ResponseEntity<ResponseDTO> deleteBook(@RequestParam int bookId) {
        service.deleteBook(bookId);
        ResponseDTO response = new ResponseDTO("Book deleted successfully", bookId );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateBookDetails/{getBookId}")
    public ResponseEntity<ResponseDTO> updateBook(@PathVariable int getBookId,
                                                  @Valid @RequestBody BookDetailsDTO bookDetailsDTO) {
        BookDetails updateBook = service.updateBookById(getBookId,bookDetailsDTO);
        ResponseDTO response = new ResponseDTO("User updated successfully", updateBook);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateBookQuantity/{getBookId}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable int getBookId,
                                                  @Valid @RequestParam int quantity) {
        BookDetails updateBookQuantity = service.updateBookQuantity(getBookId, quantity);
        ResponseDTO response = new ResponseDTO("Book Quantity updated successfully", updateBookQuantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/sortingDsc")
    public ResponseEntity<ResponseDTO> sortingBooksInDscOrder() {
        List<BookDetails> books = service.getListOfBooksInSortedDsc();
        ResponseDTO response = new ResponseDTO("Retrieved list of Books in Descending order", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/sortingAce")
    public ResponseEntity<ResponseDTO> sortingBooksInAceOrder() {
        List<BookDetails> books = service.getListOfBooksInSortedAce();
        ResponseDTO response = new ResponseDTO("Retrieved list of Books in Ascending...!", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/searchBookName/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable String bookName){
        List<BookDetails> getBookByName=service.getBookByName(bookName);
        ResponseDTO responseDTO=new ResponseDTO("Retrieved Books Successfully..!",getBookByName);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}