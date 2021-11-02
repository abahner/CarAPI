package dev.bahner.services;

import java.util.List;

import dev.bahner.beans.User;

public interface UserService {

	public User addUser(User u);
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public List<User> getAllUsers();
	public User updateUser(User change);
	public boolean deleteUser(int id);
	
}
