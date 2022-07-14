package com.bridgelabz.Model;

import com.bridgelabz.DTO.BookDetailsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_details")
@Data
@NoArgsConstructor
@ToString
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="bookId")
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;

    @ElementCollection
    @CollectionTable(name = "bookName", joinColumns = @JoinColumn(name = "bookId"))
    @Column(name = "bookName")
    private List<String> bookNames;

        public BookDetails(BookDetailsDTO bookDetailsDTO) {
        this.authorName=bookDetailsDTO.getAuthorName();
        this.bookDescription=bookDetailsDTO.getBookDescription();
        this.bookImg=bookDetailsDTO.getBookImg();
        this.bookName=bookDetailsDTO.getBookName();
        this.price=bookDetailsDTO.getPrice();
        this.quantity=bookDetailsDTO.getQuantity();
        this.bookNames=bookDetailsDTO.getBookNames();
    }
}
