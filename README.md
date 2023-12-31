# Vaartalap Application

Vaartalap is an innovative social media application, inspired by Twitter, developed using the Java programming language and Spring Boot framework. This platform strives to replicate the core functionalities of Twitter while offering a unique user experience. Users can create and share tweets, connect with others by following their accounts, express their appreciation by liking tweets, and spread interesting content by retweeting.

To build Vaartalap, a diverse range of technologies and frameworks have been employed. The application leverages the power of Spring Security, which provides robust authentication and authorization mechanisms, ensuring secure access to user accounts and protecting sensitive information. Swagger UI is utilized to create an interactive and user-friendly API documentation, making it easier for developers and users to understand the available endpoints and their functionalities.

## Skills and Technologies

- Java
- Spring Boot
- MySQL
- Spring Security
- Swagger UI
- Lombok
- Maven
- MVC Architecture
- SQL
- Hibernate
- JPQL
- JWT Token

The application leverages these skills and technologies to implement complex systems like Twitter and ensure a secure and robust application.

## Features

- User Management: Create and update user accounts, including profile information such as username, full name, email, and bio.
- Tweet Management: Create, update, and delete tweets. View and search tweets based on keywords. Retrieve a user's own tweets and view tweet details.
- Follower Management: Follow and unfollow other users. Retrieve a user's followed users.
- Like Management: Like and unlike tweets. Retrieve a user's liked tweets.
- Retweet Management: Retweet and update retweets. Retrieve a user's retweets.
- Security: Implement Spring Security to secure the application, including user authentication and authorization using JWT Tokens.
- API Documentation: Swagger UI integration for interactive API documentation and testing.

## Validation Rules

**Users:**
- Validated variables:
  - `userName`: Should contain at least 3 characters.
  - `fullName`: Should contain at least 3 characters.
  - `email`: Should be a valid email format.
  - `password`: Should contain at least 8 characters, including at least one digit, one lowercase letter, one uppercase letter, and one special character.
  - No specific validation rules for other variables.

**Tweet:**
- Validated variables:
  - `content`: Should have at least 1 character.
  - No specific validation rules for other variables.

**Retweets:**
- Validated variables:
  - `content`: Should have at least 1 character.
  - No specific validation rules for other variables.
    
## API Endpoints

### User Controller

- **GET /users**: Retrieves a list of all users.
- **POST /users**: Creates a new user.
- **POST /users/update/{userId}**: Updates the details of a specific user.
- **GET /welcome**: Retrieves a welcome message.
- **GET /users/{userId}**: Retrieves details of a specific user.
- **GET /users/search-user/{name}**: Searches for users based on the provided name.
- **GET /users/my-account**: Retrieves the account details of the currently logged-in user.
- **GET /users/ban-user/{userId}**: Bans a specific user.
- **GET /users/ban-user/un-ban/{userId}**: Unbans a specific user.
- **GET /sign-in**: Retrieves the sign-in page.

### Tweet Controller

- **GET /tweets**: Retrieves a list of all tweets.
- **POST /tweets**: Creates a new tweet.
- **POST /tweets/update/{tweetId}**: Updates the content of a specific tweet.
- **GET /tweets/{tweetId}**: Retrieves details of a specific tweet.
- **DELETE /tweets/{tweetId}**: Deletes a specific tweet.
- **GET /tweets/search-tweet/{keyword}**: Searches for tweets based on the provided keyword.
- **GET /tweets/my-tweets**: Retrieves all tweets of the currently logged-in user.

### Retweet Controller

- **POST /retweets/{tweetId}**: Retweets a specific tweet.
- **POST /retweets/update/{retweetId}**: Updates the content of a specific retweet.
- **GET /retweets**: Retrieves a list of all retweets.
- **GET /retweets/{retweetId}**: Retrieves details of a specific retweet.
- **DELETE /retweets/{retweetId}**: Deletes a specific retweet.
- **GET /retweets/my-retweets**: Retrieves all retweets made by the currently logged-in user.

### Like Controller

- **GET /likes/{tweetId}**: Retrieves the likes count for a specific tweet.
- **GET /likes/remove-like-tweet/{tweetId}**: Removes a like from a specific tweet.
- **GET /likes/my-liked-tweets**: Retrieves all tweets liked by the currently logged-in user.

### Follower Controller

- **GET /followers/{followedUserId}**: Retrieves details of a specific follower.
- **GET /followers/unfollow/{followedUserId}**: Unfollows a specific user.
- **GET /followers/my-followed-users**: Retrieves all users followed by the currently logged-in user.

## Developer Information

This application was developed by [Shimbhu Kumawat](https://github.com/Shimbhu77).

## GitHub Repository

You can find the source code for the Vaartalap application on [GitHub](https://github.com/Shimbhu77/Vaarta)
