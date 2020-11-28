package com.karpov.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karpov.app.exception.RecordNotFoundException;
import com.karpov.app.model.NotesEntity;
import com.karpov.app.repository.NotesRepository;

@Service
public class NotesService {

    @Autowired
    NotesRepository repository;

    /**
     * получить список всех заметок
     *
     * @return список заметок
     */
    public List<NotesEntity> getAllNotes() {
        List<NotesEntity> result = (List<NotesEntity>) repository.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Найти заметку по ID
     *
     * @param id
     * @return заметку или exception
     * @throws RecordNotFoundException
     */
    public NotesEntity getNoteById(Long id) throws RecordNotFoundException {  //
        Optional<NotesEntity> note = repository.findById(id);
        if (note.isPresent()) {
            return note.get();
        } else {
            throw new RecordNotFoundException("Нет записей с данным ID");
        }
    }

    /**
     * Внести новую или изменить существующую заметку//Если не найден ID -
     * добавить
     *
     * @param entity
     * @return
     */
    public NotesEntity createOrUpdateNote(NotesEntity entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);
            return entity;
        } else {
            Optional<NotesEntity> note = repository.findById(entity.getId());
            if (note.isPresent()) {                           /////////////////////////////
                NotesEntity newNote = note.get();
                newNote.setDateOfCreate(entity.getDateOfCreate());

                newNote.setName(entity.getName());
                newNote.setDescription(entity.getDescription());
                newNote = repository.save(newNote);
                return newNote;
            } else {
                entity = repository.save(entity);
                return entity;
            }
        }
    }

    public void deleteNoteById(Long id) throws RecordNotFoundException {
        Optional<NotesEntity> note = repository.findById(id);
        if (note.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Record not found with ID");
        }
    }

    public List<NotesEntity> getAllByName(String name) {
        List<NotesEntity> result = (List<NotesEntity>) repository.findByNameContainingIgnoreCase(name);
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }
 
    public List<NotesEntity> getAllByDescription(String description) {
        List<NotesEntity> result = (List<NotesEntity>) repository.findByDescriptionContainingIgnoreCase(description);
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();

        }
    }
}    

