package com.shimbhu.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Followers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer followerId;
	
	@ManyToOne
	@JoinColumn(name = "followed_user_id")
	private Users followedUser;

	@ManyToOne
	@JoinColumn(name = "follower_user_id")
	private Users followerUser;
	
    private LocalDateTime createdAt;
		
}
