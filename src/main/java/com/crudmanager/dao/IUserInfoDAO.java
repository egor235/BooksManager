package com.crudmanager.dao;
import java.util.List;


import com.crudmanager.model.UserInfo;
import com.crudmanager.model.Book;

public interface IUserInfoDAO {
	UserInfo getActiveUser(String userName);
	List<Book> getAllUserArticles();
}