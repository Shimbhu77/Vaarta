package com.shimbhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Followers;

@Repository
public interface FollowerRepository extends JpaRepository<Followers, Integer>,PagingAndSortingRepository<Followers, Integer> {

}
