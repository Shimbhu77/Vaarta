package com.shimbhu.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shimbhu.model.Users;
import com.shimbhu.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> opt = userRepository.findByEmail(username);
		
		if(opt.isPresent())
		{
			Users user = opt.get();
			
			return new MyUserDetails(user);
		}
		else
		{
			throw new UsernameNotFoundException("Invalid Username or Email ");
		}
		
	}

}