package com.medicare.productservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.medicare.productservice.dto.ProductCartResponseDto;
import com.medicare.productservice.dto.ProductRequestDto;
import com.medicare.productservice.dto.ProductResponseDto;
import com.medicare.productservice.entity.Product;

@Mapper(componentModel="spring")
public interface ProductMapper {

	public Product productRequestDtoToProduct (ProductRequestDto productRequestDto);
	
	public ProductResponseDto productToProductResponseDto (Product product);
	
	public List<ProductResponseDto> listProdutToProductResponseDto (List<Product> product);
	
	public ProductCartResponseDto productToProductCartResponseDto (Product product);
	
	public List<Product> listProductRequestDtoToProduct (List<ProductRequestDto> productRequestDto);
}
