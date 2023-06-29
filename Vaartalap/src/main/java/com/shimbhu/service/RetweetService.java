package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.RetweetsException;
import com.shimbhu.model.Retweets;
import com.shimbhu.model.dto.RetweetsDTO;

public interface RetweetService {

    public Retweets postRetweets(RetweetsDTO dto) throws RetweetsException;
	
	public Retweets getRetweetsById(Integer retweetsId) throws RetweetsException;
	
	public Retweets updateRetweets(RetweetsDTO dto,Integer retweetsId) throws RetweetsException;
	
	public Retweets deleteRetweets(Integer retweetsId) throws RetweetsException;
		
	public List<Retweets> getAllMyRetweetss() throws RetweetsException;
}
