package com.sap.Conversession.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.Conversession.model.User;
import com.sap.Conversession.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	// Method to register a user
	public void register(User user) {
		user.setStatus("online");
		userRepository.save(user);
	}

	// Method to login a user
	public User login(User user) {
		User existingUser = userRepository.findByEmail(user.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!existingUser.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("Password incorrect");
		}

		existingUser.setStatus("online");
		userRepository.save(existingUser); // Update the status
		return existingUser;
	}

	// Method to logout a user
	public void logout(String email) {
		User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		existingUser.setStatus("offline");
		userRepository.save(existingUser); // Update the status
	}

	// Method to fetch all users
	public List<User> findAll() {
		return userRepository.findAll();
	}

	// Method to update a user's details, including email, username, and password
	public void update(String email, User user) {
		User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		existingUser.setEmail(user.getEmail());
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(user.getPassword());
		existingUser.setStatus(user.getStatus());

		userRepository.save(existingUser);
	}

	// Method to delete a user
	public boolean deleteByEmailAndPassword(String email, String password) {
		User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		if (!existingUser.getPassword().equals(password)) {
			return false;
		} else {
			userRepository.delete(existingUser);
			return true;
		}
	}

	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		User existingUser = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		if (!existingUser.getPassword().equals(oldPassword)) {
			return false;
		}
		existingUser.setPassword(newPassword);
		userRepository.save(existingUser);
		return true;
	}
}
