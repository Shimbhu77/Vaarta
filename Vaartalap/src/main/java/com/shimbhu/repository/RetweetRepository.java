package com.shimbhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Retweets;

@Repository
public interface RetweetRepository extends JpaRepository<Retweets, Integer>,PagingAndSortingRepository<Retweets, Integer>  {

}
