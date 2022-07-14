package com.bridgelabz.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
    public class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid FirstName...!")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid LastName...!")
    private String lastName;
    @NotNull(message = "Email should not be null")
    private String email;
    @NotNull(message = "State should not be null")
    private String state;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate DOB;
    @NotNull(message = "Password should not be null")
    private String password;

    private List<String> userEmail;
}
