package com.shimbhu.service;

import com.shimbhu.exceptions.LikeException;
import com.shimbhu.model.Likes;

public interface LikeService {

	public Likes likeTweet(Integer tweetId) throws LikeException;
	
	public Likes dislikeTweet(Integer tweetId) throws LikeException;
	
	public Likes getAllLikeTweets() throws LikeException;
}
