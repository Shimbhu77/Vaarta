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

import com.shimbhu.exceptions.RetweetsException;
import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Retweets;
import com.shimbhu.model.dto.RetweetsDTO;
import com.shimbhu.service.RetweetService;

@RestController
public class RetweetController {

	@Autowired 
	private RetweetService retweetService;
	
	
	  @PostMapping("/retweets/{tweetId}")
	   public ResponseEntity<Retweets> postRetweet(@Validated @RequestBody RetweetsDTO dto,@PathVariable("tweetId") Integer tweetId) throws RetweetsException, UserException, TweetException
	   {
		  Retweets retweet = retweetService.postRetweets(dto,tweetId);
		   
		   return new ResponseEntity<Retweets>(retweet,HttpStatus.CREATED);
	   }
	   
	   @PostMapping("/retweets/update/{retweetId}")
	   public ResponseEntity<Retweets> updateRetweet(@Validated @RequestBody RetweetsDTO dto,@PathVariable("retweetId") Integer retweetId) throws RetweetsException, UserException
	   {
		   Retweets retweet = retweetService.updateRetweets(dto,retweetId);
		   
		   return new ResponseEntity<Retweets>(retweet,HttpStatus.ACCEPTED);
	   }
	   
	   @GetMapping("/retweets/{retweetId}")
	   public ResponseEntity<Retweets> getRetweetById(@PathVariable("retweetId") Integer retweetId) throws RetweetsException
	   {
		   Retweets retweet = retweetService.getRetweetsById(retweetId);
		   
		   return new ResponseEntity<Retweets>(retweet,HttpStatus.OK);
	   }
	   
	   @DeleteMapping("/retweets/{retweetId}")
	   public ResponseEntity<Retweets> getDeleteRetweetById(@PathVariable("retweetId") Integer retweetId) throws RetweetsException, UserException
	   {
		   Retweets retweet = retweetService.deleteRetweets(retweetId);
		   
		   return new ResponseEntity<Retweets>(retweet,HttpStatus.OK);
	   }
	   
	   @GetMapping("/retweets")
	   public ResponseEntity<List<Retweets>> AllReTweets() throws RetweetsException, UserException
	   {
		   List<Retweets> retweets = retweetService.getAllRetweets();
		   
		   return new ResponseEntity<List<Retweets>>(retweets,HttpStatus.OK);
	   }
	   
	   @GetMapping("/retweets/my-retweets")
	   public ResponseEntity<List<Retweets>> AllMyReTweets() throws RetweetsException, UserException
	   {
		   List<Retweets> retweets = retweetService.getAllMyRetweets();
		   
		   return new ResponseEntity<List<Retweets>>(retweets,HttpStatus.OK);
	   }
}
