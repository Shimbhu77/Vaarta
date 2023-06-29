package com.shimbhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Users;
import com.shimbhu.model.dto.UserDTO;
import com.shimbhu.service.UserService;

@RestController
public class UserController {

   @Autowired
   private UserService userService;
   
   @Autowired
   private PasswordEncoder passwordEncoder;
   
   @PostMapping("/users")
   public ResponseEntity<Users> signupUser(@Validated @RequestBody UserDTO dto) throws UserException
   {
	   dto.setPassword(passwordEncoder.encode(dto.getPassword()));
	   Users user = userService.registerUser(dto);
	   
	   return new ResponseEntity<Users>(user,HttpStatus.CREATED);
   }
   
   @PostMapping("/users/update/{userId}")
   public ResponseEntity<Users> updateUser(@Validated @RequestBody UserDTO dto,@PathVariable("userId") Integer userId) throws UserException
   {
	   Users user = userService.updateUser(dto,userId);
	   
	   return new ResponseEntity<Users>(user,HttpStatus.ACCEPTED);
   }
   
   @GetMapping("/users/{userId}")
   public ResponseEntity<Users> getUserById(@PathVariable("userId") Integer userId) throws UserException
   {
	   Users user = userService.getUserById(userId);
	   
	   return new ResponseEntity<Users>(user,HttpStatus.OK);
   }
   
   @GetMapping("/welcome")
   public ResponseEntity<String> welcome() throws UserException
   {
	   String message  = "Welcome to Shimbhu Vaartalap Website";
	   
	   return new ResponseEntity<String>(message,HttpStatus.OK);
   }
   
   @GetMapping("/users")
   public ResponseEntity<List<Users>> AllUsers() throws UserException
   {
	   List<Users> user = userService.getAllUser();
	   
	   return new ResponseEntity<List<Users>>(user,HttpStatus.OK);
   }
   
   @GetMapping("/sign-in")
	public ResponseEntity<Users> getLoggedInUserDetailsHandler(Authentication auth) throws BadCredentialsException, UserException{
		
		Users user= userService.getUserByEmail(auth.getName());
		
		if(user!=null)
		{
			 return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		}
		
		throw new BadCredentialsException("Invalid Username or password");
		

	}
	
	
}

