package com.medicare.productservice.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Integer productId;

    private String productName;

    private BigDecimal originalPrice;

    private BigDecimal sellingPrice;
    
    private String sku; // Stock Keeping Unit

    private Integer stockQuantity;

    private String description;

    private String imageUrl;

    private Boolean isActive;
	
}
