package com.shimbhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer>,PagingAndSortingRepository<Tweet, Integer>  {

}
