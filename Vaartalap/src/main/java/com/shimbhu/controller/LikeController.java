package com.shimbhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shimbhu.exceptions.LikeException;
import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Likes;
import com.shimbhu.service.LikeService;

@RestController
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
    @GetMapping("/likes/{tweetId}")
    public ResponseEntity<Likes> likeTweet(@PathVariable("tweetId") Integer tweetId) throws LikeException, UserException, TweetException
    {
	   Likes like = likeService.likeTweet(tweetId);
	   
	   return new ResponseEntity<Likes>(like,HttpStatus.CREATED);
    }
	 
	 @GetMapping("/likes/dislike/{tweetId}")
	 public ResponseEntity<Likes> disliketweet(@PathVariable("tweetId") Integer tweetId,@RequestParam("likeId") Integer likeId) throws UserException, TweetException, LikeException
	 {
		   Likes like = likeService.dislikeTweet(tweetId,likeId);
		   
		   return new ResponseEntity<Likes>(like,HttpStatus.CREATED);
	 }
	 
	 @GetMapping("/likes/my-all-likes")
	 public ResponseEntity<List<Likes>> getMyAllLikeTweets() throws UserException, LikeException
	 {
		 List<Likes> likes = likeService.getAllMyLikeTweets();
		 
		 return new ResponseEntity<List<Likes>>(likes,HttpStatus.CREATED);
	 }

}
