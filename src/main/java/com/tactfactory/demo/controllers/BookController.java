package com.tactfactory.demo.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.repositories.BookRepository;
import com.tactfactory.demo.repositories.UserRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = {"/buyer"})
	public String indexBuyer(Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {

		List<Book> allBooks = repository.findAll();
		List<Book> AllBooksOnSale = new ArrayList<Book>();
		List<Book> myBooks = new ArrayList<Book>();
		List<Book> myBooksNotOnSale = new ArrayList<Book>();
		List<User> allUsers = userRepository.findAll();
		int myWallet = 0;
		User myUser = null;


		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {

				if (cookie.getName().equals("role")) {
					if(!cookie.getValue().equals("buyer")) {
						response.sendRedirect("/seller");
					}
					model.addAttribute("role", cookie.getValue());
				}
				if (cookie.getName().equals("firstname")) {      
					model.addAttribute("firstname", cookie.getValue());
				}
				if (cookie.getName().equals("lastname")) {
					model.addAttribute("lastname", cookie.getValue());
				}
				if (cookie.getName().equals("error")) {
					model.addAttribute("error", URLDecoder.decode( cookie.getValue(), "UTF-8" ));
				}
			}
		}


		for(User user: allUsers) {
			if(user.getFirstname().equals(model.getAttribute("firstname")) && user.getLastname().equals(model.getAttribute("lastname"))) {
				myBooks = user.getBooks();
				myWallet = user.getWallet();
				myUser = user;
			}
		}


		for(Book book: allBooks) {
			if(book.isOnSale() && book.getUser() != myUser) {
				AllBooksOnSale.add(book);
			}
		}


		for(Book book: myBooks) {
			if(!book.isOnSale()) {
				myBooksNotOnSale.add(book);
			}
		}




		model.addAttribute("wallet", myWallet); 
		model.addAttribute("user", myUser);
		model.addAttribute("myBooks", myBooksNotOnSale);  
		model.addAttribute("books", AllBooksOnSale);  
		model.addAttribute("user_id", myUser.getId());



		return "/books/indexBuyer";
	}




	@PostMapping(value = {"/buyer"})
	public String indexBuyerFiltre(@RequestParam final int nbPage, Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {

		List<Book> allBooks = repository.findAll();
		List<Book> AllBooksOnSale = new ArrayList<Book>();
		List<Book> myBooks = new ArrayList<Book>();
		List<Book> myBooksNotOnSale = new ArrayList<Book>();
		List<User> allUsers = userRepository.findAll();
		int myWallet = 0;
		User myUser = null;


		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {

				if (cookie.getName().equals("role")) {
					if(!cookie.getValue().equals("buyer")) {
						response.sendRedirect("/seller");
					}
					model.addAttribute("role", cookie.getValue());
				}
				if (cookie.getName().equals("firstname")) {      
					model.addAttribute("firstname", cookie.getValue());
				}
				if (cookie.getName().equals("lastname")) {
					model.addAttribute("lastname", cookie.getValue());
				}
				if (cookie.getName().equals("error")) {
					model.addAttribute("error", URLDecoder.decode( cookie.getValue(), "UTF-8" ));
				}
			}
		}


		for(User user: allUsers) {
			if(user.getFirstname().equals(model.getAttribute("firstname")) && user.getLastname().equals(model.getAttribute("lastname"))) {
				myBooks = user.getBooks();
				myWallet = user.getWallet();
				myUser = user;
			}
		}


		for(Book book: allBooks) {
			if(book.isOnSale() && book.getUser() != myUser && book.getNbPage() < nbPage) {
				AllBooksOnSale.add(book);
			}
		}


		for(Book book: myBooks) {
			if(!book.isOnSale()) {
				myBooksNotOnSale.add(book);
			}
		}




		model.addAttribute("wallet", myWallet); 
		model.addAttribute("user", myUser);
		model.addAttribute("myBooks", myBooksNotOnSale);  
		model.addAttribute("books", AllBooksOnSale);  
		model.addAttribute("user_id", myUser.getId());



		return "/books/indexBuyer";
	}






	@GetMapping(value = {"/seller"})
	public String indexSeller(Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {


		List<Book> myBooks = new ArrayList<Book>();
		List<Book> myBooksOnSale = new ArrayList<Book>();
		List<Book> myBooksNotOnSale = new ArrayList<Book>();
		List<User> allUsers = userRepository.findAll();
		int myWallet = 0;
		User myUser = null;


		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("role")) {
					if(!cookie.getValue().equals("seller")) {
						response.sendRedirect("/buyer");
					}
					model.addAttribute("role", cookie.getValue());
				}
				if (cookie.getName().equals("firstname")) {     
					model.addAttribute("firstname", cookie.getValue());
				}
				if (cookie.getName().equals("lastname")) {
					model.addAttribute("lastname", cookie.getValue());
				}
			}
		}


		for(User user: allUsers) {
			if(user.getFirstname().equals(model.getAttribute("firstname")) && user.getLastname().equals(model.getAttribute("lastname"))) {
				myBooks = user.getBooks();
				myWallet = user.getWallet();
				myUser = user;
			}
		}


		for(Book book: myBooks) {
			if(book.isOnSale()) {
				myBooksOnSale.add(book);
			}else {
				myBooksNotOnSale.add(book);
			}
		}

		model.addAttribute("wallet", myWallet); 
		model.addAttribute("user", myUser);
		model.addAttribute("myBooksOnSale", myBooksOnSale);  
		model.addAttribute("myBooksNotOnSale", myBooksNotOnSale);  
		model.addAttribute("user_id", myUser.getId());


		return "/books/indexSeller";
	}

	@PostMapping(value = {"/seller"})
	public void sell(@RequestParam final Long id, @RequestParam final int price, final HttpServletResponse response) throws IOException {

		response.addCookie(new Cookie("price", String.valueOf(price)));

		response.sendRedirect("/books/sell/" + id);

	}

}
