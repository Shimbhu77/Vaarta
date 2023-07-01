package com.shimbhu.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(UserException e , WebRequest req)
	{
		ErrorDetails err  = new ErrorDetails();
		err.setDescription(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(FollowerException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler( FollowerException e , WebRequest req)
	{
		ErrorDetails err  = new ErrorDetails();
		err.setDescription(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(LikeException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(LikeException e , WebRequest req)
	{
		ErrorDetails err  = new ErrorDetails();
		err.setDescription(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(RetweetsException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(RetweetsException e , WebRequest req)
	{
		ErrorDetails err  = new ErrorDetails();
		err.setDescription(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(TweetException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(TweetException e , WebRequest req)
	{
		ErrorDetails err  = new ErrorDetails();
		err.setDescription(req.getDescription(false));
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(MethodArgumentNotValidException pe)
	{
		ErrorDetails err  = new ErrorDetails();
		err.setDescription("getting Error");
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(pe.getFieldError().getDefaultMessage());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
}
