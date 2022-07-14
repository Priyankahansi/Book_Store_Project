package com.bridgelabz.service;

import com.bridgelabz.DTO.BookDetailsDTO;
import com.bridgelabz.DTO.CartDTO;
import com.bridgelabz.Exception.UserException;
import com.bridgelabz.Model.BookDetails;
import com.bridgelabz.Model.CartDetails;
import com.bridgelabz.Model.UserModel;
import com.bridgelabz.Repository.IBookDetailsRepository;
import com.bridgelabz.Repository.ICartRepository;
import com.bridgelabz.Repository.IUserRepository;
import com.bridgelabz.Util.EmailSenderService;
import com.bridgelabz.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService sender;
    @Autowired
    ICartRepository cartRepo;
    @Autowired
    IBookDetailsRepository bookRepo;
    @Autowired
    IUserRepository userRepo;

    @Override
    public String getMessage() {

        return "Welcome to BookStore";
    }

    @Override
    public CartDetails addToCart(CartDTO cartDTO) {
        Optional<UserModel> isUserPresent = userRepo.findById(cartDTO.getUserId());
        if (isUserPresent.isPresent()) {
            BookDetails book = bookRepo.findById(cartDTO.getBookId()).orElseThrow();
            CartDetails cart = new CartDetails(isUserPresent.get(), book, cartDTO.getQuantity());
            return cartRepo.save(cart);
        }
        else throw new UserException("InValid User");
    }

    @Override
    public Optional<CartDetails> getCartById(int getCartId) {
        Optional<CartDetails> cartDetails = cartRepo.findById(getCartId);
        if (cartDetails.isPresent()) {
            return cartDetails;
        } else {
            throw new UserException("Cart Id not found");
        }
    }

    @Override
    public List<CartDetails> getListOfCartDetails() {
        List<CartDetails> cart = cartRepo.findAll();
        return cart;
    }

    @Override
    public void deleteBook(int cartId) {

        cartRepo.deleteById(cartId);
    }

    @Override
    public List<CartDetails> deleteAll() {
        cartRepo.deleteAll();
        return null;
    }

    @Override
    public CartDetails updateCart(int cartId, CartDTO cartDTO) {
        Optional<CartDetails> cartDetails = cartRepo.findById(cartId);
        cartDetails.get().setQuantity(cartDTO.getQuantity());
        cartRepo.save(cartDetails.get());
        return cartDetails.get();
    }
}