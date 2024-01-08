package com.gp.pf.user.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gp.pf.user.service.UserService;
import com.gp.pf.user.service.dto.UserRequest;
import com.gp.pf.user.service.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	final private UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody UserRequest userRequest) {
		userService.createUser(userRequest);
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getAllUser() {
		return userService.getAllUser();
	}

}
