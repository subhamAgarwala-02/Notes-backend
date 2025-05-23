package com.subham.Notes_backend.services;

import com.subham.Notes_backend.dto.NoteDTO;
import com.subham.Notes_backend.dto.NoteRequestDTO;
import com.subham.Notes_backend.dto.UserDTO;
import com.subham.Notes_backend.model.NoteModel;
import com.subham.Notes_backend.model.UserModel;
import com.subham.Notes_backend.repo.NoteRepo;
import com.subham.Notes_backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    @Autowired
    private NoteRepo noteRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    // Convert NoteDTO to NoteModel
    private NoteModel convertToEntity(NoteRequestDTO noteRequestDTO) {
        NoteModel noteModel = new NoteModel();
        noteModel.setNote(noteRequestDTO.getNote());
        noteModel.setTags(noteRequestDTO.getTags());

        return noteModel;
    }

    // Convert NoteModel to NoteDTO
    private NoteDTO convertToDTO(NoteModel noteModel) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(noteModel.getId());
        noteDTO.setNote(noteModel.getNote());
        noteDTO.setTags(noteModel.getTags());
        noteDTO.setCurrentDateTime(noteModel.getCurrentDateTime());
        noteDTO.setUpdatedDateTime(noteModel.getUpdatedDateTime());

        // Convert UserModel to UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(noteModel.getUserModel().getUsername());
        noteDTO.setUser(userDTO); // Corrected to set the DTO properly

        return noteDTO;
    }

    // Add Note
    public void addNote(NoteRequestDTO noteRequestDTO, String token) {
        String username = jwtService.extractUsername(token.substring(7)); // Extract username from JWT token

        // Ensure that we are fetching the correct user from the database
        UserModel user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new NoteModel using the request data
        NoteModel noteModel = convertToEntity(noteRequestDTO);
        noteModel.setUserModel(user); // Associate the note with the user

        noteRepo.save(noteModel); // Save the note in the database
    }

    // Get all Notes for a user
    public List<NoteDTO> getAllNotes(String token) {
        String username = jwtService.extractUsername(token.substring(7)); // Extract username from JWT token

        List<NoteModel> notes = noteRepo.findByUser_Username(username); // Find all notes by username

        // Convert each NoteModel to NoteDTO and return the list
        return notes.stream()
                .map(this::convertToDTO) // this is shorthand for (note -> this.convertToDTO(note))
                .collect(Collectors.toList());
    }

    // Delete Note by Id
    public void deleteNoteWithId(int id, String token) {
        String username = jwtService.extractUsername(token.substring(7)); // Extract username from JWT token

        // Find the note by Id
        NoteModel note = noteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // Check if the user is authorized to delete this note
        if (!note.getUserModel().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized access to delete note");
        }

        noteRepo.deleteById(id); // Delete the note
    }

    // Update Note by Id
    public void updateNoteById(int id, NoteRequestDTO noteRequestDTO, String token) {
        String username = jwtService.extractUsername(token.substring(7)); // Extract username from JWT token

        // Find the existing note by Id
        NoteModel existingNote = noteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // Check if the user is authorized to update this note
        if (!existingNote.getUserModel().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized access to update note");
        }

        // Update the existing note with new data
        existingNote.setNote(noteRequestDTO.getNote());
        existingNote.setTags(noteRequestDTO.getTags());

        noteRepo.save(existingNote); // Save the updated note
    }
}
