package com.shimbhu.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.exceptions.LikeException;
import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Likes;
import com.shimbhu.model.Tweet;
import com.shimbhu.model.Users;
import com.shimbhu.repository.LikeRepository;
import com.shimbhu.repository.UserRepository;
import com.shimbhu.service.LikeService;
import com.shimbhu.service.TweetService;
import com.shimbhu.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TweetService tweetservice;
	
	@Override
	public Likes likeTweet(Integer tweetId) throws LikeException, UserException, TweetException {
		
        Users user = userService.getCurrentLoggedInUser();
		
		Tweet tweet = tweetservice.getTweetById(tweetId);
		
		List<Likes> likeList = user.getLikes();
		
		for(Likes like : likeList)
		{
			if(like.getTweet().getTweetId()==tweet.getTweetId())
			{
				throw new LikeException("Tweet already liked with id : "+tweetId);
			}
		}
		
		
		Likes like = new Likes();
		
		like.setTweet(tweet);
		like.setUser(user);
		
		user.getLikes().add(like);
		
		tweet.getLikes().add(like);
		
		tweet.setTweetLikes(tweet.getTweetLikes()+1);
		
		log.info("tweet is liked "+tweetId);
		
		return likeRepository.save(like);
	}

	@Override
	public Likes dislikeTweet(Integer tweetId,Integer id) throws LikeException, UserException, TweetException  {
		
		 Users user = userService.getCurrentLoggedInUser();
			
		 Tweet tweet = tweetservice.getTweetById(tweetId);
		 
		 Optional<Likes> opt = likeRepository.findById(id);
		 
		 if(opt.isPresent())
		 {
			 Likes like = opt.get();
			 
			 Users likeUser = like.getUser();
			 
			 if(user.getUserId()==likeUser.getUserId())
			 {
				 
				
				 List<Likes> userLikeList = likeUser.getLikes();
				 
				 log.info("removing like from user like list. ");
				 
				 userLikeList.remove(like);
				 
				 log.info("removed like from user like list. ");
				 
                 List<Likes> tweetLikeList = tweet.getLikes();
				 
                 log.info("removing like from tweet like list. ");
                 
                 tweetLikeList.remove(like);
				 
                 log.info("removed like from tweet like list. ");
                 
				 like.setUser(null);
				 like.setTweet(null);
				 
				 log.info("null value setted");
				 
				 tweet.setTweetLikes(tweet.getTweetLikes()-1);
				 
				 likeRepository.delete(like);
				 
				 return like;
				 
				 
			 }
			 throw new LikeException("Access Denied : you don't have authority to delete this like with Id :  "+id);
		 }
		
		 throw new LikeException("can't find like with Id :  "+id);
	}

	@Override
	public List<Likes> getAllMyLikeTweets() throws LikeException, UserException {
		
		Users user = userService.getCurrentLoggedInUser();
		
		return user.getLikes();
	}

	
}
