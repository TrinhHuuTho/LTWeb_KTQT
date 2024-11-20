package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.config.UserInfoUserDetails;
import com.example.demo.entities.UserInfor;
import com.example.demo.repository.UserInforRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	UserInforRepository repository;
	
	public UserInfoService(UserInforRepository userInforRepository) {
		this.repository = userInforRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfor> userInfor = repository.findByName(username);
		
		return userInfor.map(UserInfoUserDetails :: new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found: "+ username));
		
	}

}
