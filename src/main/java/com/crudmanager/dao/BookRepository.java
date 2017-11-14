package com.crudmanager.dao;

import com.crudmanager.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
	
	Book findByAuthor(String author);

}
