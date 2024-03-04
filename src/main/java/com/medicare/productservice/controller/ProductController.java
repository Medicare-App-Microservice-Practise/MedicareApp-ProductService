package com.medicare.productservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.productservice.dto.ProductRequestDto;
import com.medicare.productservice.dto.ProductResponseDto;
import com.medicare.productservice.exception.custom.ServiceException;
import com.medicare.productservice.response.ProductResponse;
import com.medicare.productservice.services.ProductServices;



@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	@Autowired
	ProductServices service;
	
	@Autowired
	ProductResponse response;

	@PostMapping("")
	public ResponseEntity<Object> addProduct(@RequestBody @Valid ProductRequestDto productRequestDto) throws ServiceException {
		
	
		service.addProduct(productRequestDto);
		return response.responseWithoutData("success", HttpStatus.CREATED);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable int id, @RequestBody @Valid ProductRequestDto productRequestDto) throws ServiceException
	{
		ProductResponseDto productResponseDto = service.updateProduct(id, productRequestDto);
		return response.responseWithData("success", productResponseDto, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable int id) throws ServiceException
	{
		ProductResponseDto productResponseDto = service.getProduct(id);
		return response.responseWithData("success", productResponseDto, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<Object> getProducts()
	{
		List<ProductResponseDto> productResponseDto = service.getProducts();
		return response.responseWithListData("success", productResponseDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable int id)
	{
		service.deleteProduct(id);
		return response.responseWithoutData("success", HttpStatus.OK);
	}
	
}
