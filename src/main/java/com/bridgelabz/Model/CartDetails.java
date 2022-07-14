package com.bridgelabz.Model;

import com.bridgelabz.DTO.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "cart_details")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="cartId")
    private int cardId;

    @OneToOne
    @JoinColumn(name = "user_model_id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "book_details_book_id")
    private BookDetails bookDetails;

    private int quantity;



//    public CartDetails(CartDTO cartDTO) {
//        this.quantity=cartDTO.getQuantity();
//    }

    public CartDetails(UserModel userModel, BookDetails book, int quantity) {
        this.userModel=userModel;
        this.bookDetails=book;
       this.quantity=quantity;
    }
}

//    public CartDetails(UserModel userModel, BookDetails book, int quantity) {
//       this.userModel=userModel;
//       this.bookDetails=book;
//       this.quantity=quantity;
//    }

//    public CartDetails(int userId, int bookId, int quantity) {
//        this.
//    }

