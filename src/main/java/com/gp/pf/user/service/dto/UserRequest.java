package com.gp.pf.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String address;
}
