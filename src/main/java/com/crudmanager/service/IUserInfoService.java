package com.crudmanager.service;

import java.util.List;

import com.crudmanager.model.Book;
import org.springframework.security.access.annotation.Secured;

import com.crudmanager.model.Book;

public interface IUserInfoService {
	 @Secured ({"ROLE_ADMIN"})
     List<Book> getAllUserArticles();
}
