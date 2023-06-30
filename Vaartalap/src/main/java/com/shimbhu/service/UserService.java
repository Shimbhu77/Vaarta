package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Users;
import com.shimbhu.model.dto.UserDTO;

public interface UserService {

	public Users registerUser(UserDTO dto) throws UserException;
	
	public Users getUserById(Integer userId) throws UserException;
	
	public Users getUserByEmail(String email) throws UserException;
	
	public Users updateUser(UserDTO dto,Integer userId) throws UserException;
	
	public Users deleteUser(Integer userId) throws UserException;
	
	public Users banUser(Integer userId) throws UserException;
	
	public List<Users> getAllUser() throws UserException;
	
	public Users getCurrentLoggedInUser() throws UserException;
	
	
	
	
}
