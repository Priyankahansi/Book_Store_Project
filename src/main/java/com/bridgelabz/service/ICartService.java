package com.bridgelabz.service;

import com.bridgelabz.DTO.BookDetailsDTO;
import com.bridgelabz.DTO.CartDTO;
import com.bridgelabz.Model.CartDetails;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    String getMessage();


    CartDetails addToCart(CartDTO cartDTO);

    Optional<CartDetails> getCartById(int getCartId);

    List<CartDetails> getListOfCartDetails();

    void deleteBook(int cartId);

    List<CartDetails> deleteAll();

    CartDetails updateCart(int cartId,CartDTO cartDTO);

}
