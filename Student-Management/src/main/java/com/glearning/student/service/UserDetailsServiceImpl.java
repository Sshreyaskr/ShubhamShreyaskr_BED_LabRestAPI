package com.glearning.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.glearning.student.config.MyUserDetails;
import com.glearning.student.model.User;
import com.glearning.student.repository.UserRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = this.userRepository.findByUsername(username)
				         .orElseThrow(()-> new UsernameNotFoundException("Invalid User"));
	        System.out.println("User from the repository ");
	        System.out.println(user);
	       	        
	        return new MyUserDetails(user);
	}

}