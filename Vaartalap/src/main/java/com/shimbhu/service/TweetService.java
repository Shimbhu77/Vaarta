package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.TweetException;
import com.shimbhu.model.Tweet;
import com.shimbhu.model.dto.TweetDTO;

public interface TweetService {

    public Tweet postTweet(TweetDTO dto) throws TweetException;
	
	public Tweet getTweetById(Integer tweetId) throws TweetException;
	
	public Tweet updateTweet(TweetDTO dto,Integer tweetId) throws TweetException;
	
	public Tweet deleteTweet(Integer tweetId) throws TweetException;
		
	public List<Tweet> getAllMyTweets() throws TweetException;
}