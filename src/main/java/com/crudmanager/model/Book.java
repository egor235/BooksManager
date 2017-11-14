package com.crudmanager.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Book {
    private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = AUTO)
     private long id;
     private String title;
     private String author;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
