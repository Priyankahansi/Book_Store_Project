package com.bridgelabz.service;

import com.bridgelabz.DTO.LoginDTO;
import com.bridgelabz.DTO.ResponseDTO;
import com.bridgelabz.DTO.UserDTO;
import com.bridgelabz.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String getMessage();

    String addUser(UserDTO userDTO);

    Optional<UserModel> getUserData(String token);

    Optional<UserModel> getUserById(int getId);

    List<UserModel> getListOfUsers();

    void deleteUser(int id);

    UserModel updateUser(int getId, UserDTO userDTO);

   Optional<UserModel> getUserByEmail(String email);

    String loginValidation(LoginDTO loginDTO);

    UserModel forgotPassword(String emailId, String newPassword);



//    Optional<UserModel> getUserByMail(String getEmail);

}
