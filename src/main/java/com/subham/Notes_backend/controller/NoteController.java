package com.subham.Notes_backend.controller;

import com.subham.Notes_backend.model.NoteModel;
import com.subham.Notes_backend.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/addNote")
    public String createNote(@RequestBody NoteModel noteModel){
        noteService.addNote(noteModel);
        return "Note added";
    }

    @GetMapping("/allNotes")
    public List<NoteModel> allNotes(){
        return noteService.getAllNotes();
    }

    @DeleteMapping("/note/{id}")
    public String deleteNote(@PathVariable("id") int id){
        noteService.deleteNoteWithId(id);
        return "Note deleted...";
    }

    @PutMapping("/note/{id}")
    public String updateNote(@PathVariable("id") int id, @RequestBody NoteModel noteModel){
        noteService.updateNoteById(id, noteModel);
        return "Note updated...";
    }
}
