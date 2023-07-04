package com.shimbhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shimbhu.exceptions.FollowerException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Followers;
import com.shimbhu.model.Users;
import com.shimbhu.service.FollowerService;

@RestController
public class FollowerController {

	@Autowired
	private FollowerService followerService;
	
	
	@GetMapping("/followers/{followedUserId}")
    public ResponseEntity<Users> followUser(@PathVariable("followedUserId") Integer followedUserId) throws FollowerException, UserException
    {
	   Users follower = followerService.followUser(followedUserId);
	   
	   return new ResponseEntity<Users>(follower,HttpStatus.CREATED);
    }
	 
	 @GetMapping("/followers/unfollow/{followedUserId}")
	 public ResponseEntity<Users> unfollowUser(@PathVariable("followedUserId") Integer followedUserId) throws UserException, FollowerException
	 {
		   Users follower = followerService.unfollowUser(followedUserId);
		   
		   return new ResponseEntity<Users>(follower,HttpStatus.ACCEPTED);
	 }
	 
	 @GetMapping("/followers/my-followed-users")
	 public ResponseEntity<List<Followers>> getMyAllFollowedUser() throws UserException, FollowerException
	 {
		 List<Followers> likes = followerService.getAllFollowedUser();
		 
		 return new ResponseEntity<List<Followers>>(likes,HttpStatus.OK);
	 }
}
