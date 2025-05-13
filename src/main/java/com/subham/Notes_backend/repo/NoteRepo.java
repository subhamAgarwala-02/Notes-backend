package com.subham.Notes_backend.repo;

import com.subham.Notes_backend.dto.NoteDTO;
import com.subham.Notes_backend.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<NoteModel, Integer> {
    List<NoteModel> findByUser_Username(String username);
}
