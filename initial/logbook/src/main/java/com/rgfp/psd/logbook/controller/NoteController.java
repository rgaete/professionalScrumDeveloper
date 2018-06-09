package com.rgfp.psd.logbook.controller;

import com.rgfp.psd.logbook.domain.Note;
import com.rgfp.psd.logbook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(value="/")
    public String noteList(Model model) {
        model.addAttribute("noteList", noteService.findAll());
        return "noteList";
    }

    @RequestMapping(value={"/noteEdit","/noteEdit/{id}"}, method = RequestMethod.GET)
    public String noteEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("note", noteService.findOne(id));
        } else {
            model.addAttribute("note", new Note());
        }
        return "noteEdit";
    }

    @RequestMapping(value="/noteEdit", method = RequestMethod.POST)
    public String noteEdit(Model model, Note note) {
        noteService.saveNote(note);
        model.addAttribute("noteList", noteService.findAll());
        return "noteList";
    }

    @RequestMapping(value="/noteDelete/{id}", method = RequestMethod.GET)
    public String noteDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        noteService.deleteNote(id);
        model.addAttribute("noteList", noteService.findAll());
        return "noteList";
    }

}