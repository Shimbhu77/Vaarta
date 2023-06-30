package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.RetweetsException;
import com.shimbhu.exceptions.TweetException;
import com.shimbhu.exceptions.UserException;
import com.shimbhu.model.Retweets;
import com.shimbhu.model.dto.RetweetsDTO;

public interface RetweetService {

    public Retweets postRetweets(RetweetsDTO dto,Integer tweetId) throws RetweetsException, UserException, TweetException;
	
	public Retweets getRetweetsById(Integer retweetsId) throws RetweetsException;
	
	public Retweets updateRetweets(RetweetsDTO dto,Integer retweetsId) throws RetweetsException, UserException;
	
	public Retweets deleteRetweets(Integer retweetsId) throws RetweetsException, UserException;
		
	public List<Retweets> getAllMyRetweets() throws RetweetsException, UserException;
	
	public List<Retweets> getAllRetweets() throws RetweetsException, UserException;
}
