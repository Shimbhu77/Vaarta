package com.shimbhu.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.exceptions.RetweetsException;
import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Retweets;
import com.shimbhu.model.Tweet;
import com.shimbhu.model.Users;
import com.shimbhu.model.dto.RetweetsDTO;
import com.shimbhu.repository.RetweetRepository;
import com.shimbhu.repository.TweetRepository;
import com.shimbhu.repository.UserRepository;
import com.shimbhu.service.RetweetService;
import com.shimbhu.service.TweetService;
import com.shimbhu.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RetweetServiceImpl implements RetweetService {

	@Autowired
	private RetweetRepository retweetRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private TweetService tweetservice;
	
	@Override
	public Retweets postRetweets(RetweetsDTO dto,Integer tweetId) throws RetweetsException, UserException, TweetException {
		
		Users user = userService.getCurrentLoggedInUser();
		
		Tweet tweet = tweetservice.getTweetById(tweetId);
		
		Retweets retweet = new Retweets();
		
		retweet.setUser(user);
		retweet.setCreatedTweetAt(LocalDateTime.now());
		retweet.setUpdatedTweetAt(LocalDateTime.now());
		retweet.setRetweetContent(dto.getRetweetContent());
		retweet.setTweet(tweet);
		
		user.getRetweets().add(retweet);
		
		tweet.getRetweets().add(retweet);
		
		log.info("retweet adding to user.");
		
//		userRepository.save(user);
		
		log.info("retweet added to user.");
		
		log.info("retweet adding to tweet.");
		
//		tweetRepository.save(tweet);
		
		log.info("retweet added to tweet.");
		
		return retweetRepository.save(retweet);
	}

	@Override
	public Retweets getRetweetsById(Integer retweetsId) throws RetweetsException {

		Optional<Retweets> opt = retweetRepository.findById(retweetsId);
		
		if(opt.isPresent())
		{
			log.info("Retweets got by id.");
			
			return opt.get();
		}
		
		throw new RetweetsException("no retweet found with this Id : "+retweetsId);
	}

	@Override
	public Retweets updateRetweets(RetweetsDTO dto, Integer retweetsId) throws RetweetsException, UserException {
		

		Users user = userService.getCurrentLoggedInUser();
		
		Optional<Retweets> opt = retweetRepository.findById(retweetsId);
		
		 
		
		if(opt.isPresent())
		{
			Retweets retweet = opt.get();
			
			Users retweetUser = retweet.getUser();
			
			if(retweetUser.getUserId()==user.getUserId())
			{
				retweet.setRetweetContent(dto.getRetweetContent());
				retweet.setUpdatedTweetAt(LocalDateTime.now());
				
				log.info("retweet is updated");
				
				return retweetRepository.save(retweet);
			}
			
			throw new UserException("Access Denied : you don't have authority to update this retweet with Id :  "+retweetsId);
			
		}
		
		throw new RetweetsException("no tweet found with this Id : "+retweetsId);
	}

	@Override
	public Retweets deleteRetweets(Integer retweetsId) throws RetweetsException, UserException {

		Users user = userService.getCurrentLoggedInUser();
		
		Optional<Retweets> opt = retweetRepository.findById(retweetsId);
		
		if(opt.isPresent())
		{
			Retweets retweet = opt.get();
			
			Users retweetUser = retweet.getUser();
			
			Tweet tweet = retweet.getTweet();
			
			if(retweetUser.getUserId()==user.getUserId())
			{
				List<Retweets> list = user.getRetweets();
				
				log.info("retweet is removing from user list ");
				
				list.remove(retweet);
				
				log.info("retweet is removed from user list ");
				

				List<Retweets> tweetList = tweet.getRetweets();
				
				log.info("retweet is removing from tweetList ");
				
				tweetList.remove(retweet);
				
				log.info("retweet is removed from tweetList ");
				
				retweet.setUser(null);
				retweet.setTweet(null);
				
				log.info("retweet is deleting");
				
				retweetRepository.delete(retweet);
				
				log.info("retweet is deleted");
				
//				tweet.getRetweets().remove(retweet);
				
				return retweet;
			}
			
			throw new UserException("Access Denied : you don't have authority to update this retweet with Id :  "+retweetsId);
			
		}
		
		throw new RetweetsException("no tweet found with this Id : "+retweetsId);
	}

	@Override
	public List<Retweets> getAllMyRetweets() throws RetweetsException, UserException {

		Users user = userService.getCurrentLoggedInUser();
		
		List<Retweets> retweets = user.getRetweets();
		 
		log.info("All My Retweets got.");
		
		return retweets;
	}

	@Override
	public List<Retweets> getAllRetweets() throws RetweetsException, UserException {

		Users user = userService.getCurrentLoggedInUser();
		
		List<Retweets> retweets = retweetRepository.findAll();
		 
		log.info("All Retweets got.");
		
		return retweets;
	}

}
