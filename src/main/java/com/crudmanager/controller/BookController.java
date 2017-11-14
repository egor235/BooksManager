package com.crudmanager.controller;

import java.util.ArrayList;
import java.util.List;

import com.crudmanager.model.Book;
import com.crudmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crudmanager.dao.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookrepository;

	@Autowired
	private BookService bookservice;
	private ArrayList<Book> bookModelList;

	@GetMapping(value = "/")
	public String bookHome(
			@RequestParam(value = "search", required = false) String author,
			Model model) {
		if (author != null) {
			bookModelList = new ArrayList<Book>();
			System.out.println("q is = " + author);
			List<String> booklist = bookservice.atriskBooks(author);
			for (String name : booklist) {
				System.out.println("Books in repository are : " + bookrepository.findAll());
				Book doggy = bookrepository.findByAuthor(author);
				System.out.println(doggy.toString() + "book title : " + doggy.getTitle());
				bookModelList.add(doggy);
				System.out.println("This book`s title is : " + doggy.getTitle());
			}
		}
		model.addAttribute("search", bookModelList);
		model.addAttribute("books", bookrepository.findAll());
		return "index";
	}

	@PostMapping(value = "/")
	public String addBook(@RequestParam("title") String title,
			@RequestParam("author") String author, Model model) {
		bookservice.addBook(title,author);
		System.out.println("title = " + title + ",author= " + author);
		return "redirect:/";
	}

	@PostMapping(value = "/delete")
	public String deleteBook(@RequestParam("title") String title,
			@RequestParam("id") Long id) {
		bookservice.deleteBook(title, id);
		System.out.println("Book title = " + title + "was removed from our database. Hopefully he or she was adopted.");
		return "redirect:/";

	}
	
	@PostMapping(value = "/genkey")
	public String genkey(@RequestParam("title") String title,
			@RequestParam("author") String author, Model model) {
		bookservice.getGeneratedKey(title, author);
		System.out.println("title = " + title + ",author = " + author);
		return "redirect:/";
	}

}
