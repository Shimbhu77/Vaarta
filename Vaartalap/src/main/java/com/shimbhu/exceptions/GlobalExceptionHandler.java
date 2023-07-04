package com.shimbhu.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

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
	
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException ex) {
	        List<String> errorMessages = new ArrayList<>();
	        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	            errorMessages.add(violation.getMessage());
	        }

	        ErrorDetails errorDetails = new ErrorDetails();
	        errorDetails.setDescription("Validation failed");
	        errorDetails.setTimestamp(LocalDateTime.now());
	        errorDetails.setMessage("Validation error : "+errorMessages);
//	        errorDetails.setErrors(errorMessages);

	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(BadCredentialsException.class)
	 public ResponseEntity<ErrorDetails> myExceptionHandler(BadCredentialsException e) {
	     ErrorDetails err = new ErrorDetails();
	     err.setDescription("Invalid credentials");
	     err.setTimestamp(LocalDateTime.now());
	     err.setMessage(e.getMessage());
	     
	     return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
	 }

}
