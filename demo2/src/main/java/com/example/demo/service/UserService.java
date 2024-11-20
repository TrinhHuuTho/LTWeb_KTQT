package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserInfor;
import com.example.demo.repository.UserInforRepository;

@Service
public record UserService(UserInforRepository repository, PasswordEncoder passwordEncoder) {
	public String addUser(UserInfor userInfor) {
		userInfor.setPassword(passwordEncoder.encode(userInfor.getPassword()));
		repository.save(userInfor);
		return "Them user thanh cong";
	}
	
}
