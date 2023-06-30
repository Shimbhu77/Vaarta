package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.LikeException;
import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Likes;

public interface LikeService {

	public Likes likeTweet(Integer tweetId) throws LikeException, UserException, TweetException;
	
	public Likes dislikeTweet(Integer tweetId,Integer id) throws LikeException, UserException, TweetException;
	
	public List<Likes> getAllMyLikeTweets() throws LikeException, UserException;
}
