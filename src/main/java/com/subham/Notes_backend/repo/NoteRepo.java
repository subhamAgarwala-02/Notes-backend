package com.subham.Notes_backend.repo;

import com.subham.Notes_backend.model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<NoteModel, Integer> {
}
