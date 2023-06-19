package com.backend.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler
	private ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e)
	{
		String message=e.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false,HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> response=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			response.put(fieldname, message);
		});
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	private ResponseEntity<ApiResponse> handleApiException(ApiException e)
	{
		String message=e.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false,HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
}
