package dev.bahner.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import dev.bahner.beans.User;
import dev.bahner.repositories.UserRepo;
import dev.bahner.services.UserService;

@SpringBootTest(classes = dev.bahner.runner.CarApiApplication.class)
public class UserServiceTests {

	@Autowired
	public UserService us;

	@MockBean
	UserRepo ur;

	@Test
	public void addUserSuccess() {
		User u = new User("user", "pass");
		Mockito.when(ur.save(u)).thenReturn(new User(u.getId(), u.getUsername(), u.getPassword()));

		u = us.addUser(u);

		assertEquals("user", u.getUsername());
		assertEquals("pass", u.getPassword());
	}

	@Test
	public void addUserFail() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(true);

		assertThrows(ResponseStatusException.class, () -> {
			us.addUser(u);
		});

	}

	@Test
	public void getUserByIdSuccess() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(true);
		Mockito.when(ur.findById(u.getId())).thenReturn(Optional.of(u));

		us.getUserById(u.getId());

		assertEquals("user", u.getUsername());
		assertEquals("pass", u.getPassword());

	}

	@Test
	public void getUserByIdFail() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(false);

		assertThrows(ResponseStatusException.class, () -> {
			us.getUserById(u.getId());
		});
	}
	
	@Test
	public void getUserByUnameSuccess() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(true);
		Mockito.when(ur.findById(u.getId())).thenReturn(Optional.of(u));

		us.getUserByUsername(u.getUsername());

		assertEquals("user", u.getUsername());
		assertEquals("pass", u.getPassword());

	}

	@Test
	public void getUserByUnameFail() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(false);
		
		assertThat(us.getUserByUsername(u.getUsername()) == null);
	}

	@Test
	public void updateUserSuccess() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(true);
		Mockito.when(ur.save(u)).thenReturn(new User("updatedUname", "updatedPword"));
		u = us.updateUser(u);
		
		assertEquals("updatedUname", u.getUsername());
		assertEquals("updatedPword", u.getPassword());
	}

	@Test
	public void updateUserFail() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(false);

		assertThrows(ResponseStatusException.class, () -> {
			us.updateUser(u);
		});
	}

	@Test
	public void deleteUserSuccess() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(true);
		
		assertEquals(us.deleteUser(u.getId()), true);
	}

	@Test
	public void deleteUserFail() {
		User u = new User("user", "pass");
		Mockito.when(ur.existsById(u.getId())).thenReturn(false);
		
		assertThrows(ResponseStatusException.class, () -> {
			us.deleteUser(u.getId());
		});
	}

	@Test
	public void getAllUsers() {
		User u = new User("user", "pass");
		List<User> users = new ArrayList<User>();
		users.add(u);
		Mockito.when(ur.findAll()).thenReturn(users);
		assertNotNull(users);
	}

}
