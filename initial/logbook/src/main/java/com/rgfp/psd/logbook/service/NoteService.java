package com.rgfp.psd.logbook.service;

import com.rgfp.psd.logbook.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> findAll() {
        return (List<Note>) noteRepository.findAll();
    }

    public Optional<Note> findOne(Long id) {
        return noteRepository.findById(id);
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
