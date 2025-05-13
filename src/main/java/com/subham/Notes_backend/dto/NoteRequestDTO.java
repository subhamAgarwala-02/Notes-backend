package com.subham.Notes_backend.dto;

import jakarta.persistence.Entity;


public class NoteRequestDTO {
    private String note;
    private String tags;

    // Getters and Setters

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
