package com.karpov.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="TABLE_NOTES")
public class NotesEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id", nullable = false)
    private Long id;
    
    @Column(name="name", length=30, nullable = false )
    private String name;
    
    @Column(name="dateofcreate")
    
   // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOfCreate;
    
    @Transient
    private String passedTime; 
    
    @Column(name="description",  length=200)
    private String description;

    public NotesEntity() {
    }

   
    @Override
    public String toString() {
        return "NotesEntity{" + "id=" + id + ", name=" + name + ", dateOfCreate=" + dateOfCreate + ", description=" + description + '}';
    }

//    public void setDateOfCreate(LocalDateTime now) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}