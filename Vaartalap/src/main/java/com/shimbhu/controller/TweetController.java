package com.shimbhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Tweet;
import com.shimbhu.model.dto.TweetDTO;
import com.shimbhu.service.TweetService;

@RestController
public class TweetController {
	
	   @Autowired
	   private TweetService tweetservice;
	
	   @PostMapping("/tweets")
	   public ResponseEntity<Tweet> signuptweet(@Validated @RequestBody TweetDTO dto) throws TweetException, UserException
	   {
		   Tweet tweet = tweetservice.postTweet(dto);
		   
		   return new ResponseEntity<Tweet>(tweet,HttpStatus.CREATED);
	   }
	   
	   @PostMapping("/tweets/update/{tweetId}")
	   public ResponseEntity<Tweet> updatetweet(@Validated @RequestBody TweetDTO dto,@PathVariable("tweetId") Integer tweetId) throws TweetException, UserException
	   {
		   Tweet tweet = tweetservice.updateTweet(dto,tweetId);
		   
		   return new ResponseEntity<Tweet>(tweet,HttpStatus.ACCEPTED);
	   }
	   
	   @GetMapping("/tweets/{tweetId}")
	   public ResponseEntity<Tweet> gettweetById(@PathVariable("tweetId") Integer tweetId) throws TweetException
	   {
		   Tweet tweet = tweetservice.getTweetById(tweetId);
		   
		   return new ResponseEntity<Tweet>(tweet,HttpStatus.OK);
	   }
	   
	   @DeleteMapping("/tweets/{tweetId}")
	   public ResponseEntity<Tweet> deleteTweetById(@PathVariable("tweetId") Integer tweetId) throws TweetException, UserException
	   {
		   Tweet tweet = tweetservice.deleteTweet(tweetId);
		   
		   return new ResponseEntity<Tweet>(tweet,HttpStatus.OK);
	   }
	   
	   @GetMapping("/tweets")
	   public ResponseEntity<List<Tweet>> AllTweet() throws TweetException, UserException
	   {
		   List<Tweet> tweet = tweetservice.getAllTweets();
		   
		   return new ResponseEntity<List<Tweet>>(tweet,HttpStatus.OK);
	   }
	   
	   @GetMapping("/tweets/my-tweets")
	   public ResponseEntity<List<Tweet>> AllMyTweet() throws TweetException, UserException
	   {
		   List<Tweet> tweet = tweetservice.getAllMyTweets();
		   
		   return new ResponseEntity<List<Tweet>>(tweet,HttpStatus.OK);
	   }
	   
	   @GetMapping("/tweets/search-tweet/{keyword}")
	   public ResponseEntity<List<Tweet>> searchTweetsByHashTag(@PathVariable("keyword") String keyword) throws TweetException, UserException
	   {
		   List<Tweet> tweets = tweetservice.searchTweetByHashTag(keyword);
		   
		   return new ResponseEntity<List<Tweet>>(tweets,HttpStatus.OK);
	   }
}
