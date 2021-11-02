package dev.bahner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.bahner.beans.User;
import dev.bahner.services.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserService us;
	
	@GetMapping(value = "/users", produces = "application/json")
	public List<User> getAllUsers() {
		return us.getAllUsers();
	}
	
	@GetMapping(value = "/users/{id}")
	public User getUserById(@PathVariable("id") String id) {
		return us.getUserById(Integer.parseInt(id));
	}
	
	@GetMapping(value = "/users")
	public User getUserByUsername(@RequestParam(required = false) String username) {
		return us.getUserByUsername(username);
	}
	
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public User addUser(@RequestBody User u) {
		return us.addUser(u);
	}
	@PutMapping(value = "/users/{id}", consumes = "application/json", produces = "application/json")
	public User updateUser(@PathVariable int id, @RequestBody User change) {
		change.setId(id);
		return us.updateUser(change);
	}
	
	@DeleteMapping("/users/{id}")
	public boolean deleteUser(@PathVariable int id) {
		us.deleteUser(id);
		return true;
	}
	
	
	
	
}
