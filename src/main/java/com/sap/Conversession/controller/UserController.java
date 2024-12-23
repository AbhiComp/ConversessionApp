package com.sap.Conversession.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.Conversession.model.User;
import com.sap.Conversession.services.UserServiceImplementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

	private final UserServiceImplementation service;

	@PostMapping
	public void register(@RequestBody User user) {
		service.register(user);
	}

	@PostMapping("/login")
	public User login(@RequestBody User user) {
		return service.login(user);
	}

	@PostMapping("/logout")
	public void logout(@RequestBody User email) {
		service.logout(email.getEmail());
	}

	@GetMapping
	public List<User> findAll() {
		return service.findAll();
	}

	@PutMapping("/{email}")
	public void update(@PathVariable String email, @RequestBody User user) {
		service.update(email, user);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody User user) {
		boolean deleted = service.deleteByEmailAndPassword(user.getEmail(), user.getPassword());
		if (deleted) {
			return ResponseEntity.ok("User deleted successfully");
		} else {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Invalid email or password");
		}
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody User user) {
	    boolean updated = service.updatePassword(user.getEmail(), user.getPassword(), user.getNewPassword());
	    if (updated) {
	        return ResponseEntity.ok("Password updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or old password");
	    }
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception ex) {
		ex.printStackTrace();
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
