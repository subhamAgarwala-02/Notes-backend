package com.subham.Notes_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class NoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String Note;
    private String CurrentDateTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public String getUpdatedDateTime() {
        return UpdatedDateTime;
    }

    public void setUpdatedDateTime(String updatedDateTime) {
        UpdatedDateTime = updatedDateTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private String UpdatedDateTime;
    private List<String> tags;
}
