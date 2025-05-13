package com.subham.Notes_backend.controller;

import com.subham.Notes_backend.dto.NoteDTO;
import com.subham.Notes_backend.dto.NoteRequestDTO;
import com.subham.Notes_backend.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Add Note
    @PostMapping("/addNote")
    public ResponseEntity<String> createNote(@RequestBody NoteRequestDTO noteDTO, @RequestHeader("Authorization") String token){
        noteService.addNote(noteDTO, token);
        return new ResponseEntity<>("Note added successfully", HttpStatus.CREATED);
    }

    // Get all Notes
    @GetMapping("/allNotes")
    public ResponseEntity<List<NoteDTO>> allNotes(@RequestHeader("Authorization") String token){
        List<NoteDTO> notes = noteService.getAllNotes(token);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    // Update Note by Id
    @PutMapping("/note/{id}")
    public ResponseEntity<String> updateNote(@PathVariable("id") int id, @RequestBody NoteRequestDTO noteDTO, @RequestHeader("Authorization") String token){
        noteService.updateNoteById(id, noteDTO, token);
        return new ResponseEntity<>("Note updated successfully", HttpStatus.OK);
    }

    // Delete Note by Id
    @DeleteMapping("/note/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable("id") int id, @RequestHeader("Authorization") String token){
        noteService.deleteNoteWithId(id, token);
        return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);
    }
}
