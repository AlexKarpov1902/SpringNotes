package com.karpov.app.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.karpov.app.exception.RecordNotFoundException;
import com.karpov.app.model.NotesEntity;
import com.karpov.app.service.NotesService;
import com.karpov.app.service.TimePass;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class NotesController {

    @Autowired
    NotesService service;

    /**
     * Вывести список всех заметок
     *
     * @param model
     * @return
     */
    @RequestMapping
    public String getAllNotes(Model model) //
    {
        List<NotesEntity> list = service.getAllNotes();
        list.forEach(note -> {
            note.setPassedTime(TimePass.getTimePass(note.getDateOfCreate()));
        });
        model.addAttribute("notes", list);
        return "list-notes";
    }

    /**
     * Редактировать заметку с определенным  id
     *
     * @param model
     * @param id
     * @return
     * @throws RecordNotFoundException
     */
    @RequestMapping(path = {"/edit", "/edit/{id}"})                      //
    public String editNotesById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        NotesEntity entity;
        if (id.isPresent()) {
            entity = service.getNoteById(id.get());
        } else {
            entity = new NotesEntity();
            entity.setDateOfCreate(LocalDateTime.now());
        }
        model.addAttribute("note", entity);
        return "add-edit-note";
    }

    /**
     * Удалить заметку по id
     * @param model
     * @param id  id заметки
     * @return
     * @throws RecordNotFoundException
     */
    @RequestMapping(path = "/delete/{id}")   //
    public String deleteNoteById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteNoteById(id);
        return "redirect:/";
    }

    /**
     * Добавить новую заметку
     *
     * @param note
     * @return
     */
    @RequestMapping(path = "/createNote", method = RequestMethod.POST)
    public String createOrUpdateNote(NotesEntity note) {
        note.setDateOfCreate(LocalDateTime.now());
        service.createOrUpdateNote(note);
        return "redirect:/"; 
    }

     /**
     * Поиск заметок по названию или по содержимому
     *
     * @param model
     * @param name          подстрока для поиска в названии заметки
     * @param description   подстрока  для поиска в описании заметки
     * @return
     */
    @RequestMapping(path = "/find-notes")
    public String findNotes(Model model,
                            @RequestParam String name,
                            @RequestParam String description) {
        List<NotesEntity> list = new ArrayList();
        if (!name.isEmpty()) {
            list = service.getAllByName(name);
        } else if (!description.isEmpty()) {
            list = service.getAllByDescription(description);
        }
        list.forEach(note -> {
            note.setPassedTime(TimePass.getTimePass(note.getDateOfCreate()));
        });
        model.addAttribute("notes", list);
        return "list-notes";
    }    
}    
