package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.FollowerException;
import com.shimbhu.model.Users;

public interface FollowerService {

	public Users followUser(Integer followedUserId, Integer followerUserId) throws FollowerException;
	
	public Users unfollowUser(Integer followedUserId, Integer followerUserId) throws FollowerException;
	
	public List<Users> getAllFollowedUser() throws FollowerException;
}
