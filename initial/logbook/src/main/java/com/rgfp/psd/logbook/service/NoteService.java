package com.rgfp.psd.logbook.service;

import com.rgfp.psd.logbook.domain.Note;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    @Setter
    private NoteRepository noteRepository;

    private List<Note> allNotes;
    private HashMap<String, Integer> dictionary;

    @PostConstruct
    public void syncAllNotes() {
        this.allNotes = this.findAll();
        this.updateDictionary();
    }

    private void updateDictionary() {
        dictionary = new HashMap<>();
        String unwantedCharacters = "[,|.|:|?|!]";

        for(Note note : allNotes) {
            for (String word: note.getContent().toLowerCase().replaceAll(unwantedCharacters, "").split(" ")) {
                if (dictionary.containsKey(word)) {
                    dictionary.replace(word, dictionary.get(word) + 1);
                } else {
                    dictionary.put(word, 1);
                }
            }
        }
    }

    public List<Note> findAll() {
        List<Note> noteList = (List<Note>) noteRepository.findAll();
        this.allNotes = noteList;
        return noteList;

    }

    public Optional<Note> findOne(Long id) {
        return noteRepository.findById(id);
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
        this.syncAllNotes();
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
        this.syncAllNotes();
    }

    List<Note> getAllNotes() {
        return allNotes;
    }

    public List<String> getRepeatedWords(Integer repetitionFactor) {

        ArrayList<String> repeatedWords = new ArrayList<>();

        for (String key : dictionary.keySet()) {
            if (dictionary.get(key) > repetitionFactor) {
                repeatedWords.add(key);
            }
        }
        return repeatedWords;

    }

    public List<String> getRepeatedWords() {
        return this.getRepeatedWords(2);
    }
}