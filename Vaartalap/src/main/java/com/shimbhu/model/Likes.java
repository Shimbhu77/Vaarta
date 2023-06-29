package com.shimbhu.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer likeId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tweet_id")
	private Tweet tweet;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Users user;

}
