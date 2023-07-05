package com.shimbhu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>,PagingAndSortingRepository<Users, Integer>  {

	public Optional<Users> findByEmail(String email);                                   
	public Optional<Users> findByUserName(String userName);  
	
	@Query("SELECT u FROM Users u WHERE u.fullName LIKE %:name%")
	public List<Users> searchUserByname(@Param("name") String name);
}
