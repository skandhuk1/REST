package com.in28minutes.rest.webservices.restfulwebservices.user;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {

	@Autowired
	public UserRepository userRepository;
	
	@GetMapping("/jpa//users")
	public List<User> retrieveAllusers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa//users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		
		 Optional<User> user = userRepository.findById(id);
		
		
		if(!user.isPresent())
			throw new UserNotFoundException("id="+id);
		
		//Resource<User> resource = new Resource<User>(user.get());

		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		//resource.add(linkTo.withRel("all-users"));

		// HATEOAS

		return user;
	} 
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	
}
