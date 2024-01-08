package com.gp.pf.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gp.pf.user.service.dto.UserRequest;
import com.gp.pf.user.service.dto.UserResponse;
import com.gp.pf.user.service.model.User;
import com.gp.pf.user.service.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	final private UserRepository userRepository;

	public void createUser(UserRequest userRequest) {		
		User user = User.builder().username(userRequest.getFirstName())
				.firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.address(userRequest.getAddress())
				.email(userRequest.getEmail())
				.mobile(userRequest.getMobile())
				.build();
		userRepository.save(user);
		log.info("User {} is created",user.getId());
	}
	
	public List<UserResponse> getAllUser() {		
		List<User> users = userRepository.findAll();
		return users.stream().map(this::mapToUserResponse).toList();
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.firstName(user.getUsername())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.mobile(user.getMobile())
				.address(user.getAddress())
				.build();
	}
}
