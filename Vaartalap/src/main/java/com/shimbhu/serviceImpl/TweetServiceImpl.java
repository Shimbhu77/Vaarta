package com.shimbhu.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Tweet;
import com.shimbhu.model.Users;
import com.shimbhu.model.dto.TweetDTO;
import com.shimbhu.repository.TweetRepository;
import com.shimbhu.repository.UserRepository;
import com.shimbhu.service.TweetService;
import com.shimbhu.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TweetServiceImpl implements TweetService {

	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Tweet postTweet(TweetDTO dto) throws TweetException, UserException {
		
		Users user = userService.getCurrentLoggedInUser();
		
		Tweet tweet = new Tweet();
		
		tweet.setContent(dto.getContent());
		tweet.setCreatedTweetAt(LocalDateTime.now());
		tweet.setUpdatedTweetAt(LocalDateTime.now());
		tweet.setTweetLikes(0);
		tweet.setTweetViews(0);
		tweet.setUser(user);
		
		log.info("Tweet is created");
		
		user.getTweets().add(tweet);
		
		tweetRepository.save(tweet);
		
		userRepository.save(user);	
		
		log.info("Tweet is Posted");
		
		return tweet;
	}

	@Override
	public Tweet getTweetById(Integer tweetId) throws TweetException {
		
		Optional<Tweet> opt = tweetRepository.findById(tweetId);
		
		if(opt.isPresent())
		{
			log.info("Tweet got by id.");
			Tweet tweet = opt.get();
			
			tweet.setTweetViews(tweet.getTweetViews()+1);
			
			tweetRepository.save(tweet);
			
			return tweet;
		}
		
		throw new TweetException("no tweet found with this Id : "+tweetId);
	}

	@Override
	public Tweet updateTweet(TweetDTO dto, Integer tweetId) throws TweetException, UserException {

		Users user = userService.getCurrentLoggedInUser();
		
		Optional<Tweet> opt = tweetRepository.findById(tweetId);
		
		 
		
		if(opt.isPresent())
		{
			Tweet tweet = opt.get();
			
			Users tweetUser = tweet.getUser();
			
			if(tweetUser.getUserId()==user.getUserId())
			{
				tweet.setContent(dto.getContent());
				tweet.setUpdatedTweetAt(LocalDateTime.now());
				
				log.info("Tweet is updated");
				
				return tweetRepository.save(tweet);
			}
			
			throw new UserException("Access Denied : you don't have authority to update this tweet with Id :  "+tweetId);
			
		}
		
		throw new TweetException("no tweet found with this Id : "+tweetId);
	}

	@Override
	public Tweet deleteTweet(Integer tweetId) throws TweetException, UserException {

		Users user = userService.getCurrentLoggedInUser();
		
		Optional<Tweet> opt = tweetRepository.findById(tweetId);
		
		 
		
		if(opt.isPresent())
		{
			Tweet tweet = opt.get();
			
			Users tweetUser = tweet.getUser();
			
			if(tweetUser.getUserId()==user.getUserId())
			{
				List<Tweet> list = user.getTweets();
				
				list.remove(tweet);
				
				tweet.setUser(null);
				tweet.setLikes(null);
				tweet.setRetweets(null);
				
				log.info("Tweet is deleted.");
				
				tweetRepository.delete(tweet);
				
				return tweet;
			}
			
			throw new UserException("Access Denied : you don't have authority to update this tweet with Id :  "+tweetId);
			
		}
		
		throw new TweetException("no tweet found with this Id : "+tweetId);
	}

	@Override
	public List<Tweet> getAllTweets() throws TweetException, UserException {

		Users user = userService.getCurrentLoggedInUser();
		
		List<Tweet> tweets = tweetRepository.findAll();
		 
		log.info("All Tweets got.");
		
		return tweets;
	}
	
	@Override
	public List<Tweet> getAllMyTweets() throws TweetException, UserException {
		
		Users user = userService.getCurrentLoggedInUser();
		
		List<Tweet> tweets = user.getTweets();
		
		log.info("All my Tweets got.");
		
		return tweets;
	}

}
