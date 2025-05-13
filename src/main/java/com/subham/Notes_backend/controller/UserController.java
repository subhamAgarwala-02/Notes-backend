package com.subham.Notes_backend.controller;

import com.subham.Notes_backend.dto.UserDTO;
import com.subham.Notes_backend.model.UserModel;
import com.subham.Notes_backend.services.JwtService;
import com.subham.Notes_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signUp")
    public String addUser(@RequestBody UserModel userModel){
        userService.addUser(userModel);
        return "User added...";
    }

    @PostMapping("/login")
    public String getUser(@RequestBody UserModel userModel){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword())
        );

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userModel.getUsername());
        }
        else return "User not found";
    }

}
