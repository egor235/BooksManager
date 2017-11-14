package com.crudmanager.service;


import java.util.List;

public interface BookService {

    void addBook(String title, String author);

    void deleteBook(String title, Long id);

    List<String> atriskBooks(String author);

    long getGeneratedKey(String title, String author);

}
