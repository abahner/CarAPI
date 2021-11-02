package dev.bahner.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import dev.bahner.beans.User;
import dev.bahner.repositories.UserRepo;

@CrossOrigin
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserRepo ur;
	
	@Override
	public User addUser(User u) {
		if (ur.existsById(u.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			u = ur.save(u);
			return u;
		}
	}

	@Override
	public User getUserById(int id) {
		if (ur.existsById(id)) {
			return ur.findById(id).get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public User getUserByUsername(String username) {
		return ur.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) ur.findAll();
	}

	@Override
	public User updateUser(User change) {
		if (ur.existsById(change.getId())) {
			return ur.save(change);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public boolean deleteUser(int id) {
		if (ur.existsById(id)) {
			ur.deleteById(id);
			return true;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
