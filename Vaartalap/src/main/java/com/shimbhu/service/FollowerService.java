package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.FollowerException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Followers;
import com.shimbhu.model.Users;

public interface FollowerService {

	public Users followUser(Integer followedUserId) throws FollowerException, UserException;
	
	public List<Followers> getAllFollowedUser() throws FollowerException, UserException;

	public Users unfollowUser(Integer followedUserId) throws FollowerException, UserException;
}
