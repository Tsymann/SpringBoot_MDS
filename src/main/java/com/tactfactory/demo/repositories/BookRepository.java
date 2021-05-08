package com.tactfactory.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tactfactory.demo.entities.Book;


public interface BookRepository extends JpaRepository<Book, Long>{

}
