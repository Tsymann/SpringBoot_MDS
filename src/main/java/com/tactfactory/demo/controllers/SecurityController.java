package com.tactfactory.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tactfactory.demo.entities.BaseEntity;
import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.User;
import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.repositories.RoleRepository;
import com.tactfactory.demo.repositories.UserRepository;
import com.tactfactory.demo.services.UserService;

@Controller
public class SecurityController<T extends BaseEntity, DTO> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService service;


	@GetMapping(value = {"/login"})
	public String index(final HttpServletResponse response, final HttpServletRequest request, Model model) {


		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("firstname")) {
					model.addAttribute("firstname", cookie.getValue());
				}
				if (cookie.getName().equals("lastname")) {
					model.addAttribute("lastname", cookie.getValue());
				}
				if (cookie.getName().equals("role")) {
					model.addAttribute("role", cookie.getValue());
				}
			}
		}				
		return "/login/index";
	}

	@PostMapping(value = {"/login"})
	public void log(final Model model, final HttpServletResponse response, @RequestParam final String firstname, @RequestParam final String lastname, @RequestParam final String roleStr) throws IOException {

		response.addCookie(new Cookie("firstname", firstname)); 
		response.addCookie(new Cookie("lastname", lastname)); 
		response.addCookie(new Cookie("role", roleStr)); 

		List<User> allUsers = userRepository.findAll();
		List<Role> allRoles = roleRepository.findAll();

		User myUser = null;

		boolean userAlreadyRegistered = false;
		for(User user: allUsers) {
			if(user.getFirstname().equals(firstname) && user.getLastname().equals(lastname)) {
				userAlreadyRegistered = true;
				myUser = user;
			}
		}


		for(Role role: allRoles) {
			if(role.getName().equals(roleStr)) {
				if(!userAlreadyRegistered) {
					service.saveUser(firstname, lastname, role);	
				}
			}
			if(role.getName().equals("buyer")) {
				if(userAlreadyRegistered) {
					myUser.setRole(role);
					userRepository.saveAndFlush(myUser);				
				}
			}
		}


		response.sendRedirect("/" + roleStr);
	}

	@GetMapping(value = {"/logout"})
	public void logout(final HttpServletResponse response) throws IOException {


		response.addCookie(new Cookie("firstname", null)); 
		response.addCookie(new Cookie("lastname", null)); 
		response.addCookie(new Cookie("role", null)); 


		response.sendRedirect("/login");
	}











	@GetMapping("test")
	public String test() {
		return "/testcontroller/test";
	}
	/*

	@GetMapping("test2/{id}")
	public String test2(@PathVariable Integer id) {
		System.out.println(id);
		return "/testcontroller/test";
	}

	@GetMapping("test3")
	public String test3(
			@PathParam("id") Integer id,
			@PathParam("isOk") Boolean isOk) {
		System.out.println(id);
		System.out.println(isOk);
		return "/testcontroller/test";
	}

	@GetMapping("test4")
	public String test4(final Model model) {

		model.addAttribute("model1", "coucou");
		model.addAttribute("model2", "bonjour");
		model.addAttribute("model2", "hey");

		return "/testcontroller/test4";
	}
	 */
}
