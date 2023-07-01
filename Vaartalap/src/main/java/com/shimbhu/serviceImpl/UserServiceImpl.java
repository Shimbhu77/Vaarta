package com.shimbhu.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Users;
import com.shimbhu.model.dto.UserDTO;
import com.shimbhu.repository.UserRepository;
import com.shimbhu.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Users registerUser(UserDTO dto) throws UserException {
		
		log.info(" inside registerUser method  "+dto);
		
		Users user  = new Users();
		
		user.setUserName(dto.getUserName());
		user.setFullName(dto.getFullName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setBio(dto.getBio());
		user.setRole(dto.getRole());
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		
		log.info("User is created "+user);
		
		return userRepository.save(user);
	}

	@Override
	public Users getUserById(Integer userId) throws UserException {
		
		log.info(" inside getUserById method  ");
		
		Optional<Users> opt = userRepository.findById(userId);
		
		if(opt.isPresent())
		{
			Users user = opt.get();
			
			log.info("user is retrieved:  "+user);
			return user;
		}
		throw new UserException("User not found with this Id : "+userId);
		
	}

	@Override
	public Users updateUser(UserDTO dto, Integer userId) throws UserException {

		log.info(" inside updateUser method  ");
		
		Users loggedInUser = getCurrentLoggedInUser();
		
		Optional<Users> opt = userRepository.findById(userId);
		
		if(opt.isPresent())
		{
			Users user = opt.get();
			
			if(loggedInUser.getUserId()==user.getUserId())
			{
				user.setBio(dto.getBio());
				user.setFullName(dto.getFullName());
				user.setUpdatedAt(LocalDateTime.now());
				
				log.info("User is updated "+user);
				
				return userRepository.save(user);
			}
			
			throw new UserException("Access Denied, you can't update this User with this Id : "+userId);
		}
		throw new UserException("User not found with this Id : "+userId);
	}

	@Override
	public Users deleteUser(Integer userId) throws UserException {
		
		log.info(" inside deleteUser method  ");
		
        Users loggedInUser = getCurrentLoggedInUser();
		
		Optional<Users> opt = userRepository.findById(userId);
		
		if(opt.isPresent())
		{
			Users user = opt.get();
			
			if(loggedInUser.getUserId()==user.getUserId())
			{
				
				log.info("User is deleting account "+user);
//				
//				user.setLikes(null);
//				user.setFollowers(null);
//				user.setRetweets(null);
//				user.setTweets(null);
				
//				userRepository.delete(user);
				
				log.info("User deleted account "+user);
				
				return user;
			}
			
			throw new UserException("Access Denied, you can't delete this User with this Id : "+userId);
		}
		throw new UserException("User not found with this Id : "+userId);
	}

	@Override
	public Users banUser(Integer userId) throws UserException {
		
		log.info(" inside banUser method  ");
		
		return null;
	}

	@Override
	public List<Users> getAllUser() throws UserException {
		
		log.info(" inside getAllUser method  ");
		
		log.info("All Users got  ");
		
		return userRepository.findAll();
	}

	@Override
	public Users getUserByEmail(String email) throws UserException {

		log.info(" inside getUserById method  ");
		
		Optional<Users> opt = userRepository.findByEmail(email);
		
		if(opt.isPresent())
		{
			Users user = opt.get();
			
			log.info("user is retrieved:  "+user);
			return user;
		}
		throw new UserException("User not found with this email : "+email);
	}
	
	@Override
	public Users getCurrentLoggedInUser() throws UserException
	{
		log.info(" checking user is logged in or not.");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Optional<Users> opt = userRepository.findByEmail(auth.getName());
		
		if(opt.isPresent())
		{
			Users user = opt.get();
			
			log.info("user is already logged in.");
			
			return user;
		}
		
		throw new UserException("Please Login first, for accessing your account");
		
	}

}
