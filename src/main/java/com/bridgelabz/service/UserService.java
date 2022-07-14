package com.bridgelabz.service;

import com.bridgelabz.DTO.LoginDTO;
import com.bridgelabz.DTO.ResponseDTO;
import com.bridgelabz.DTO.UserDTO;
import com.bridgelabz.Exception.UserException;
import com.bridgelabz.Model.UserModel;
import com.bridgelabz.Repository.IUserRepository;
import com.bridgelabz.Util.EmailSenderService;
import com.bridgelabz.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository repo;
    @Autowired
    TokenUtil tokenutil;
    @Autowired
    EmailSenderService sender;

    @Override
    public String getMessage() {
        return "Welcome to UserRegistration";
    }

    @Override
    public String addUser(UserDTO userDTO) {
             UserModel userModel = new UserModel(userDTO);
            repo.save(userModel);
            String token = tokenutil.createToken(userModel.getUserId());
            sender.sendEmail(userModel.getEmail(), "Test Email",
                    "Registered Successfully: " +
                            " " + userModel.toString());
        return token;
    }

    @Override
    public Optional<UserModel> getUserData(String token) {
        int id = tokenutil.decodeToken(token);
        Optional<UserModel> userModel = repo.findById(id);
        if (userModel.isPresent()) {
            return userModel;
        } else {
            throw new UserException("User not Found");
        }
    }

    @Override
    public Optional<UserModel> getUserById(int getId) {
        Optional<UserModel> userModel = repo.findById(getId);
        if (userModel.isPresent()) {
            return userModel;
        } else {
            throw new UserException("User not Found");
        }
    }

    @Override
    public List<UserModel> getListOfUsers() {
        List<UserModel> users = repo.findAll();
        return users;
    }

    @Override
    public void deleteUser(int id) {
        Optional<UserModel> userModel = repo.findById(id);
        repo.deleteById(id);
        sender.sendEmail(userModel.get().getEmail(), "Test Mail-BookStore",
                "User deleted Successfully" + userModel.toString());
    }

    @Override
    public UserModel updateUser(int getId, UserDTO userDTO) {
        Optional<UserModel> newUser = repo.findById(getId);
        if (newUser.isPresent() && newUser.get().getEmail() == userDTO.getEmail()) {
            newUser.get().setFirstName(userDTO.getFirstName());
            newUser.get().setLastName(userDTO.getLastName());
            newUser.get().setState(userDTO.getState());
            newUser.get().setEmail(userDTO.getEmail());
            newUser.get().setDOB(userDTO.getDOB());
            newUser.get().setPassword(userDTO.getPassword());
            repo.save(newUser.get());
            sender.sendEmail(newUser.get().getEmail(), "Test Mail-BookStore",
                    "To get Updated User: click here->" +
                            "http://localhost:8080/BookStore/getUser/"+getId);
            return newUser.get();
        } else {
            throw new UserException("User is invalid");
        }
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        Optional<UserModel> user = repo.findUserByEmail(email);
        return user;
    }

    @Override
    public String loginValidation(LoginDTO loginDTO) {
        Optional<UserModel> userPresent = repo.findUserByEmail(loginDTO.getEmail());
        if (userPresent.isPresent()) {
            String password = userPresent.get().getPassword();
            if (password.equals(loginDTO.getPassword())) {
//                String token = tokenutil.createToken(userPresent.get().getUserID());
                return " Welcome "+userPresent.get().getFirstName()+"  "+"Login Successfully..! ";
            } else {
                throw new UserException("Password is InCorrect");
            }
        }
        throw new UserException("User is Invalid");
    }

    @Override
    public UserModel forgotPassword(String emailId, String newPassword) {
        Optional<UserModel> isUserPresent = repo.findUserByEmail(emailId);
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setPassword(newPassword);
            return repo.save(isUserPresent.get());
        } else {
            throw new UserException("Invalid Email");
        }
    }
}






