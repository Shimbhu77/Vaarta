package com.shimbhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer>,PagingAndSortingRepository<Tweet, Integer>  {

	@Query("SELECT t FROM Tweet t WHERE t.content LIKE %:keyword%")
	public List<Tweet> searchTweetByHashTag(@Param("keyword") String keyword);
}
