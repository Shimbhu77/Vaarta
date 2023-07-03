package com.shimbhu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>,PagingAndSortingRepository<Users, Integer>  {

	public Optional<Users> findByEmail(String email);                                   
	public Optional<Users> findByUserName(String userName);                                   
}
