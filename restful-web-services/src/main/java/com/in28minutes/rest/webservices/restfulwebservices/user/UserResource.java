package com.in28minutes.rest.webservices.restfulwebservices.user;


import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllusers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id="+id);
		
			return user;
	}
	
	@PostMapping("/users")
	public void createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		//URI locaiton = ServletUriComponentsBuilder.fromCurrentRequest()
				//.path("/{id}").buildAndExpand(savedUser.getId().toString())
		
		//return ResponseEntity<T>.created(location).build();
	} 
}
