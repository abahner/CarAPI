package dev.bahner.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.bahner.beans.User;


@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
	
	public boolean existsById(int id);
	public User findByUsername(String username);

}
