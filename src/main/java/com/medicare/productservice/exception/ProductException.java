package com.medicare.productservice.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.medicare.productservice.exception.custom.NotSaveException;
import com.medicare.productservice.exception.custom.ServiceException;
import com.medicare.productservice.response.ProductResponse;

@RestControllerAdvice
public class ProductException {
	
	@Autowired
	ProductResponse response;

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> serviceException (ServiceException ex)
	{
		return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> dataViolation (DataIntegrityViolationException ex)
	{
		return response.errorResponse("fail", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
