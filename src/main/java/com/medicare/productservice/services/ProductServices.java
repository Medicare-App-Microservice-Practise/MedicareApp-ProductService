package com.medicare.productservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.productservice.dto.ProductCartResponseDto;
import com.medicare.productservice.dto.ProductRequestDto;
import com.medicare.productservice.dto.ProductResponseDto;
import com.medicare.productservice.entity.Product;
import com.medicare.productservice.exception.custom.NotFoundException;
import com.medicare.productservice.exception.custom.NotSaveException;
import com.medicare.productservice.exception.custom.ServiceException;
import com.medicare.productservice.mapper.ProductMapper;
import com.medicare.productservice.repository.ProductRepository;

@Service
public class ProductServices {
	
	@Autowired
	ProductRepository repo;
	
	@Autowired
	ProductMapper mapper;

	public void addProduct(ProductRequestDto productRequestDto) throws ServiceException {
		
		try {
			
			Product product = mapper.productRequestDtoToProduct(productRequestDto);

			Product saveProduct = repo.save(product);
			
			if(saveProduct == null)
			{
				throw new NotSaveException("Fail to save product");
			}
			
		}catch(NotSaveException ex)
		{
			throw new ServiceException(ex.getMessage(),ex);
		}
		
	}
	
	public ProductResponseDto updateProduct(int id,ProductRequestDto productRequestDto) throws ServiceException 
	{
		try {
			
			Product product = repo.findById(id).orElseThrow(()-> new NotFoundException("Product ID "+id+" Not Found"));
			
			if(productRequestDto.getDescription() != null)
			{
				product.setDescription(productRequestDto.getDescription());
			}
			if(productRequestDto.getImageUrl() != null)
			{
				product.setImageUrl(productRequestDto.getImageUrl());
			}
			if(productRequestDto.getOriginalPrice() != null)
			{
				product.setOriginalPrice(productRequestDto.getOriginalPrice());
			}
			if(productRequestDto.getProductName() != null)
			{
				product.setProductName(productRequestDto.getProductName());
			}
			if(productRequestDto.getSellingPrice() != null)
			{
				product.setSellingPrice(productRequestDto.getSellingPrice());
			}
			if(productRequestDto.getSku() != null)
			{
				product.setSku(productRequestDto.getSku());
			}
			if(productRequestDto.getStockQuantity() !=null)
			{
				product.setStockQuantity(productRequestDto.getStockQuantity());
			}
			
			Product savedProduct = repo.save(product);
			
			if(savedProduct == null)
			{
				throw new NotSaveException("Fail to Update");
			}
			
			ProductResponseDto productResponseDto = mapper.productToProductResponseDto(savedProduct);
			return productResponseDto;
		
		} catch (NotFoundException ex) {
			throw new ServiceException(ex.getMessage(),ex);
		}catch(NotSaveException ex) {
			throw new ServiceException(ex.getMessage(),ex);
		}
		
		
	}
	
	public ProductResponseDto getProduct(int id) throws ServiceException
	{
		try {
			
			Product product = repo.findById(id).orElseThrow(()-> new NotFoundException("Product ID "+id+" Not Found"));
			
			ProductResponseDto productResponseDto = mapper.productToProductResponseDto(product);
			return productResponseDto;
			
		}catch(NotFoundException ex) {
			throw new ServiceException(ex.getMessage(),ex);
		}
		
	}
	
	public List<ProductResponseDto> getProducts()
	{
		List<Product> product = repo.findAll();
		List<ProductResponseDto> productResponseDto = mapper.listProdutToProductResponseDto(product);
		return productResponseDto;
	}
	
	public void deleteProduct(int id)
	{
		repo.deleteById(id);
	}
	
	public ProductCartResponseDto getProductCart (int productId) throws NotFoundException
	{
		Product product = repo.findById(productId).orElseThrow(()-> new NotFoundException("Product ID "+productId+" Not Found"));
		ProductCartResponseDto productCartResponseDto = mapper.productToProductCartResponseDto(product);
		return productCartResponseDto;
	}
	
	public void addProductBulk (List<ProductRequestDto> productRequestDto)
	{
		List<Product> product = mapper.listProductRequestDtoToProduct(productRequestDto);
		repo.saveAll(product);
	}
	
}
