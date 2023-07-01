package com.shimbhu.model;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

	@Override
	public String toString() {
		return "Likes [likeId=" + likeId + ", tweet=" + tweet + ", user= ]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer likeId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tweet_id")
	@JsonIgnore
	private Tweet tweet;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Users user;

}
