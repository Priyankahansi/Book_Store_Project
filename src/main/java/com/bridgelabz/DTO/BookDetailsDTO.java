package com.bridgelabz.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class BookDetailsDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid FirstName...!")
    private String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid LastName...!")
    private String authorName;
    @NotNull(message = "BookDescription should not be null")
    private String bookDescription;
    @NotNull(message = "Book Image should not be null")
    private String bookImg;
    @NotNull(message = "Price should not be null")
    private int price;
    @NotNull(message = "Quantity should not be null")
    private int quantity;
    @NotNull(message = "bookNames should not be null")
    private List<String> bookNames;
}
