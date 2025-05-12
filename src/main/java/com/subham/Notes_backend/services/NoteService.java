package com.subham.Notes_backend.services;

import com.subham.Notes_backend.model.NoteModel;
import com.subham.Notes_backend.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;

    public void addNote(NoteModel noteModel) {

        noteRepo.save(noteModel);
    }

    public List<NoteModel> getAllNotes() {
        return noteRepo.findAll();
    }

    public void deleteNoteWithId(int id) {
        noteRepo.deleteById(id);
    }

    public void updateNoteById(int id, NoteModel noteModel) {
        Optional<NoteModel> existingNote = noteRepo.findById(id);

        if(existingNote.isPresent()){
            NoteModel updatedNote = existingNote.get();
            updatedNote.setNote(noteModel.getNote());
            updatedNote.setTags(noteModel.getTags());

            noteRepo.save(updatedNote);
        } else {
            throw new RuntimeException("Note not found...");
        }
    }
}
