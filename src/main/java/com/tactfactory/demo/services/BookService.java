package com.tactfactory.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tactfactory.demo.entities.Book;
import com.tactfactory.demo.entities.Role;
import com.tactfactory.demo.repositories.BookRepository;

public class BookService {

	
	@Autowired
	private BookRepository repository;

	
	public List<Book> findAll() {
		return this.repository.findAll();
	}
	
	public void addBook (Book book) {
		this.repository.save(book);
	}
	
	public Map<Long, String> getTemplateList(){
        Map<Long, String> result = new HashMap<>();

        for (Book item : this.repository.findAll()) {
            result.put(item.getId(), item.getName());
        }

        return result;
    }
}
