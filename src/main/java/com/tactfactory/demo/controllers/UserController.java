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
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = {"/users/list"})
	public String index(Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {

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
		model.addAttribute("users", allUsers);
		model.addAttribute("user_id", myUser.getId());



		return "/user/list";
	}


	@GetMapping(value = {"/users/{id}"})
	public String show(@PathVariable final Long id, Model model, final HttpServletRequest request, final HttpServletResponse response) throws IOException {

		List<User> allUsers = userRepository.findAll();
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
				myUser = user;
			}
		}
	
		
		
		

		model.addAttribute("user", myUser);
		model.addAttribute("user_id", myUser.getId());
		model.addAttribute("wallet", myUser.getWallet());


		return "/user/show";
	}

}

