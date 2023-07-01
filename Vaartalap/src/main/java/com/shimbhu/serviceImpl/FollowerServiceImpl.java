package com.shimbhu.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.exceptions.FollowerException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Followers;
import com.shimbhu.model.Users;
import com.shimbhu.repository.FollowerRepository;
import com.shimbhu.repository.UserRepository;
import com.shimbhu.service.FollowerService;
import com.shimbhu.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FollowerServiceImpl implements FollowerService {
	
	@Autowired
	private FollowerRepository followerRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users followUser(Integer followedUserId) throws FollowerException, UserException {

		Users user = userService.getCurrentLoggedInUser();
		
		Users followedUser = userService.getUserById(followedUserId);
		
		List<Followers> followerList = user.getFollowers();
		
		for(Followers follower : followerList)
		{
			if(follower.getFollowedUser().getUserId()==followedUser.getUserId())
			{
				throw new UserException("you already following user "+followedUser.getFullName());
			}
		}
		
		Followers follower = new Followers();
		
		follower.setFollowedUser(followedUser);
		follower.setFollowerUser(user);
		follower.setCreatedAt(LocalDateTime.now());
		
		user.getFollowers().add(follower);
		
		user.setFollowing(user.getFollowing()+1);
		
		followedUser.setFollower(followedUser.getFollower()+1);
		
		followerRepository.save(follower);
		
		return followedUser;
	}

	@Override
	public Users unfollowUser(Integer followedUserId) throws FollowerException, UserException {
		
		 Users user = userService.getCurrentLoggedInUser();
		 
		 Users followedUser = userService.getUserById(followedUserId);
			
//		 Optional<Followers>  opt = followerRepository.findById(followId);
		 
		 List<Followers> followerList = user.getFollowers();
		 
		 for(Followers follower : followerList)
			{
			    
			 log.info("inside loop for checking user. ");
			 
					 Users followerUser = follower.getFollowerUser();
					 
					 Users followedUser2 = follower.getFollowedUser();
					 
					 log.info("user.getUserId() : "+user.getUserId());
					 log.info("followerUser.getUserId()  : "+followerUser.getUserId());
					 log.info("followedUser.getUserId(): "+followedUser.getUserId());
					 log.info("followedUser2.getUserId() : "+followedUser2.getUserId());
					 
					 if(user.getUserId()==followerUser.getUserId() && followedUser.getUserId()==followedUser2.getUserId())
					 {
						 		
						 List<Followers> userFollowerList = followerUser.getFollowers();
						 
						 log.info("removing like from user like list. ");
						 
						 userFollowerList.remove(follower);
						 
						 log.info("removed like from user like list. ");
		                 
						 follower.setFollowedUser(null);
						 follower.setFollowerUser(null);
						 
						 log.info("null value setted");
						 
						 followerUser.setFollowing(followerUser.getFollowing()-1);
						 followedUser2.setFollower(followedUser2.getFollower()-1);
						 
						 followerRepository.delete(follower);
						 
						 log.info("unfollow completed.");
						 
						 return followedUser2;
				     }
			}
				
			throw new FollowerException("you are not following user with Id :  "+followedUserId);
	}

	@Override
	public List<Followers> getAllFollowedUser() throws FollowerException, UserException {
		
		 Users user = userService.getCurrentLoggedInUser();
		 
		 return user.getFollowers();
	}

	
}
