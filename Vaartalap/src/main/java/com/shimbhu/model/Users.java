package com.shimbhu.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Size(min=3,message = "your username should contains at least 3 character.")
	@Column(unique = true)
	private String userName;
	
	@Size(min=3,message = "your full name should contains at least 3 character.")
	private String fullName;
	
	@Email(message = "please enter a valid email.")
	@Column(unique = true)
	private String email;
	
	@Pattern(
	            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
	            message = "Invalid password. It must contain at least 8 characters, including at least one digit, one lowercase letter, one uppercase letter, and one special character."
	        )
	 @JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String bio;
	
	private String role;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private Integer follower=0;
	
	private Integer following=0;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Tweet> tweets = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Followers> followers = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Likes> likes = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Retweets> retweets = new ArrayList<>();
	
	
	
}
