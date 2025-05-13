package com.subham.Notes_backend.repo;

import com.subham.Notes_backend.dto.UserDTO;
import com.subham.Notes_backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsername(String username);
}
