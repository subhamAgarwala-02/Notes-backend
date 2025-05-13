package com.subham.Notes_backend.services;

import com.subham.Notes_backend.dto.UserDTO;
import com.subham.Notes_backend.model.UserModel;
import com.subham.Notes_backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserModel userModel) {
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);

        userRepo.save(userModel);
    }
}
