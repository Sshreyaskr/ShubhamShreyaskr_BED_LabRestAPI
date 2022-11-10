package com.glearning.student.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.glearning.student.model.User;
import com.glearning.student.model.Role;


public class MyUserDetails implements UserDetails {

	//This class acts as a bridge between spring security and users
	private final String username;
	private final String password;
	private final List<GrantedAuthority> authorities;
	
	private User user;

	public MyUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = user.getRoles()
				 				.stream()
				 				.map(Role::getRoleName)
				 				.map(roleName->"ROLE_"+roleName)
				 				.map(SimpleGrantedAuthority::new)
				 				.collect(Collectors.toList());
		//this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		System.out.println("Printing the authorities :: ");
//		
//		Set<Role> roles=user.getRoles();
//				
//		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
//
//		for(Role role:roles){
//			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//		}
//		  
//		return authorities;
		System.out.println("Printing the authorities :: ");
		System.out.println(this.authorities);
		return this.authorities;
	}

	@Override
	public String getPassword() {
		//return user.getPassword();
		return this.password;
	}

	@Override
	public String getUsername() {
		//return user.getUsername();
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
