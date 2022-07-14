package com.bridgelabz.controller;

import com.bridgelabz.DTO.BookDetailsDTO;
import com.bridgelabz.DTO.CartDTO;
import com.bridgelabz.DTO.ResponseDTO;
import com.bridgelabz.Model.BookDetails;
import com.bridgelabz.Model.CartDetails;
import com.bridgelabz.Model.UserModel;
import com.bridgelabz.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService service;

    @GetMapping("/home")
    public String getMessage() {
        String msg = service.getMessage();
        return msg;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO)  {
        CartDetails cart = service.addToCart(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added to cart Successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/CartById/{getCartId}")
    public ResponseEntity<ResponseDTO> getCartDetails(@PathVariable int getCartId) {
        Optional<CartDetails> cart= service.getCartById(getCartId);
        ResponseDTO response = new ResponseDTO("Cart details retrieved successfully for id:" + " " + getCartId, cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/CartDetails")
    public ResponseEntity<ResponseDTO> getAll() {
        List<CartDetails> cartDetailsList = service.getListOfCartDetails();
        ResponseDTO response = new ResponseDTO("Retrieved Cart Detailed List", cartDetailsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int CartId) {
        service.deleteBook(CartId);
        ResponseDTO response = new ResponseDTO("Book removed from cart", CartId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseDTO> deleteAll() {
        List<CartDetails> removeAll = service.deleteAll();
        ResponseDTO response = new ResponseDTO("Cart Empty", removeAll);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateCart/{cartId}")
    public ResponseEntity<ResponseDTO> updateCart( @PathVariable int cartId,
                                                   @RequestBody CartDTO cartDTO) {
        CartDetails updateCart= service.updateCart(cartId,cartDTO);
        ResponseDTO response = new ResponseDTO("Cart Updated successfully", updateCart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}