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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {

	@Override
	public String toString() {
		return "Tweet [tweetId=" + tweetId + ", tweetLikes=" + tweetLikes + ", tweetViews=" + tweetViews + ", content="
				+ content + ", createdTweetAt=" + createdTweetAt + ", updatedTweetAt=" + updatedTweetAt + ", likes="
				 + ", retweets=" + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tweetId;
	
	private Integer tweetLikes;
	
	private Integer tweetViews;
	
	private String content;
	
    private LocalDateTime createdTweetAt;
	
	private LocalDateTime updatedTweetAt;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Users user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Likes> likes = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Retweets> retweets = new ArrayList<>();
}
