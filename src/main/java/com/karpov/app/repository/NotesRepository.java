package com.karpov.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.karpov.app.model.NotesEntity;
import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<NotesEntity, Long> {


    List<NotesEntity> findByNameContainingIgnoreCase(String name);
    

    List<NotesEntity> findByDescriptionContainingIgnoreCase(String description);
   
}
