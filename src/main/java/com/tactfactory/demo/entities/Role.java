package com.tactfactory.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Role extends BaseEntity{
	private String name;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
	private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
