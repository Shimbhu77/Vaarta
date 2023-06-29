package com.shimbhu.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tweetId;
	
	private String content;
	
    private LocalDateTime createdTweetAt;
	
	private LocalDateTime updatedTweetAt;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Likes> likes = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Retweets> retweets = new ArrayList<>();
}
