package com.example.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends RepresentationModel<User> implements Serializable {
	private static final long serialVersionUID = 3868269731826822792L;
	
	private Long id;
	private String name;
	private LocalDate birthday;

}
