package com.shimbhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Likes;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer>,PagingAndSortingRepository<Likes, Integer> {

}
