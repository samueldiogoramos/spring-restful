package com.example.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getById(@PathVariable final Long id) {
		final User user = User.builder()
				.id(id)
				.name("Paul Jorgenson")
				.birthday(LocalDate.of(1980, 10, 30))
				.build();

		user.add(linkTo(methodOn(UserController.class).create(user)).withRel("create"));
		user.add(linkTo(methodOn(UserController.class).update(user)).withRel("update"));

		return ResponseEntity.ok(user);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<?> create(@RequestBody final User user) {

		user.add(linkTo(methodOn(UserController.class).update(user)).withRel("update"));
		user.add(linkTo(methodOn(UserController.class).getById(user.getId()))
				.withRel("getById"));

		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<User> update(@RequestBody final User user) {
		
		user.add(linkTo(methodOn(UserController.class).create(user)).withRel("create"));
		user.add(linkTo(methodOn(UserController.class).getById(user.getId()))
				.withRel("getById"));
		
		return ResponseEntity.ok(user);
	}
}
