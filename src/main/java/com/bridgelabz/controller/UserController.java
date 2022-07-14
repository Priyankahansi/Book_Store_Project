package com.bridgelabz.controller;

import com.bridgelabz.DTO.LoginDTO;
import com.bridgelabz.DTO.ResponseDTO;
import com.bridgelabz.DTO.UserDTO;
import com.bridgelabz.Model.UserModel;
import com.bridgelabz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
  IUserService service;

    @GetMapping("/hello")
    public String hello() {
        String msg = service.getMessage();
        return msg;
    }
    @PostMapping(value = "/userRegister")
    public ResponseEntity<ResponseDTO> addUserData(@Valid @RequestBody UserDTO userDTO)
    {
        String addUser = service.addUser(userDTO);
        ResponseDTO response = new ResponseDTO("User added successfully",addUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/retrieve/{token}")
    public ResponseEntity<ResponseDTO> getUserData(@PathVariable String token)
    {
        Optional<UserModel> userData = service.getUserData(token);
        ResponseDTO response = new ResponseDTO("User retrieved successfully",userData);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getUser/{getId}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable int getId) {
        Optional<UserModel> userId = service.getUserById(getId);
        ResponseDTO response = new
                ResponseDTO("User retrieved successfully by id:" +" " +getId,userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<ResponseDTO> getUsers() {
        List<UserModel> users = service.getListOfUsers();
        ResponseDTO response = new ResponseDTO("Retrieved list of users all data Successfully", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestParam int id) {
        service.deleteUser(id);
        ResponseDTO response = new ResponseDTO("User deleted successfully", id );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{getId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable int getId,
                                                  @Valid @RequestBody UserDTO userDTO) {
        UserModel updateUser = service.updateUser(getId, userDTO);
        ResponseDTO response = new ResponseDTO("User updated successfully", updateUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/Email/{email}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable String email){
        Optional<UserModel> user=service.getUserByEmail(email);
        ResponseDTO responseDTO=new ResponseDTO("Get call for User email",user);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        String userLogin= service.loginValidation(loginDTO);
        ResponseDTO responseDTO=new ResponseDTO("Login Successful..!",userLogin);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestParam String emailId,
                                                      @RequestParam String newPassword) {
        UserModel userData = service.forgotPassword(emailId, newPassword);
        ResponseDTO responseDTO = new ResponseDTO("Password Updated Successfully", userData);
        return new ResponseEntity<> (responseDTO, HttpStatus.OK);
    }
}
