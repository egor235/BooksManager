package com.crudmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudmanager.dao.IUserInfoDAO;
import com.crudmanager.model.Book;
@Service
public class UserInfoService implements IUserInfoService {
	@Autowired
	private IUserInfoDAO userInfoDAO;
	@Override
	public List<Book> getAllUserArticles(){
		return userInfoDAO.getAllUserArticles();
	}
}
