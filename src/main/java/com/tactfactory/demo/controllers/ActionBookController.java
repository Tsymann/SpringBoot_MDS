package com.tactfactory.demo.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.repositories.BookRepository;
import com.tactfactory.demo.repositories.UserRepository;

@Controller
@RequestMapping("books")
public class ActionBookController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private UserRepository userRepository;




	@GetMapping(value = {"/buy/{id}"})
	public void buy(@PathVariable final Long id, final HttpServletRequest request, final HttpServletResponse response, Model model) throws Exception {

		Book book = repository.findById(id).get();

		if(book != null) {
			if(!book.isOnSale()) {
				response.addCookie(new Cookie("error", "ce livre n'est pas à vendre")); 

				response.sendRedirect("/buyer");
			}
		}else {
			response.addCookie(new Cookie("error", "ce livre n'existe pas")); 

			response.sendRedirect("/buyer");
		}

		User seller = book.getUser();
		User buyer = null;

		List<User> allUsers = userRepository.findAll();

		String firstname = null;
		String lastname = null;

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("firstname")) {
					firstname = cookie.getValue();
				}
				if (cookie.getName().equals("lastname")) {
					lastname = cookie.getValue();
				}
			}
		}

		for(User user: allUsers) {
			if(user.getFirstname().equals(firstname) && user.getLastname().equals(lastname)) {
				buyer = user;  
			}
		}

		if(buyer.getWallet() - book.getPrice() < 0) {
			response.addCookie(new Cookie("error", URLEncoder.encode( "vous n'avez pas assez d'argent", "UTF-8" ) )); 
		}else {

			seller.setWallet(seller.getWallet()+book.getPrice());
			buyer.setWallet(buyer.getWallet()-book.getPrice());
			book.setUser(buyer);
			book.setOnSale(false);

			repository.saveAndFlush(book);
			userRepository.saveAndFlush(seller);
			userRepository.saveAndFlush(buyer);

		}

		response.sendRedirect("/buyer");
	}






	@GetMapping(value = {"/sell/{id}"})
	public void sell(@PathVariable final Long id, final HttpServletRequest request, final HttpServletResponse response, Model model) throws IOException {

		Book book = repository.findById(id).get();

		if(book != null) {
			if(book.isOnSale()) {
				model.addAttribute("error", "ce livre est déjà à vendre");
				response.sendRedirect("/seller");
			}
		}else {
			model.addAttribute("error", "ce livre n'existe pas");
			response.sendRedirect("/seller");
		}

		Integer price = null;

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("price")) {
					price = Integer.valueOf(cookie.getValue());
				}
			}
		}


		book.setPrice(price);
		book.setOnSale(true);

		repository.saveAndFlush(book);


		response.sendRedirect("/seller");
	}
	
	
	@GetMapping(value = {"/notsell/{id}"})
	public void notsell(@PathVariable final Long id, final HttpServletRequest request, final HttpServletResponse response, Model model) throws IOException {

		Book book = repository.findById(id).get();



		book.setOnSale(false);

		repository.saveAndFlush(book);


		response.sendRedirect("/seller");
	}
	
	@GetMapping(value = {"/add"})
	public String addGet(final HttpServletRequest request, final HttpServletResponse response, Model model){

		List<User> allUsers = userRepository.findAll();
		int myWallet = 0;
		User myUser = null;
		
		
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
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
				myWallet = user.getWallet();
				myUser = user;
			}
		}


		model.addAttribute("wallet", myWallet); 
		model.addAttribute("user_id", myUser.getId());

		return "books/addBook";
	}
	
	@PostMapping(value = {"/add"})
	public void addPost(@RequestParam final String name, @RequestParam final int nbPage, @RequestParam final int price, final HttpServletRequest request, final HttpServletResponse response, Model model) throws IOException {

		List<User> allUsers = userRepository.findAll();
		int myWallet = 0;
		User myUser = null;
		
		
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
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
				myWallet = user.getWallet();
				myUser = user;
			}
		}


		model.addAttribute("wallet", myWallet); 
		model.addAttribute("user_id", myUser.getId());
		
		boolean isCorrect = true;
		
		Book book = new Book();
		book.setName(name);
		book.setNbPage(nbPage);
		book.setPrice(price);
		book.setUser(myUser);
		book.setOnSale(false);
		
		repository.saveAndFlush(book);
		

		response.sendRedirect("/seller");

	}


}
