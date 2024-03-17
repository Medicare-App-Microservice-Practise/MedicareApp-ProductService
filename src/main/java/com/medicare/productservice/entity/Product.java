package com.medicare.productservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false, length = 200)
    private String productName;

    @Column(nullable = false)
    private BigDecimal originalPrice;

    @Column(nullable = false)
    private BigDecimal sellingPrice;
    
    @Column(nullable = false, unique = true, length = 30)
    private String sku; // Stock Keeping Unit
    
    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean isActive;
    
    @Column(nullable = false)
    private LocalDateTime dateAdded;

    @Column(nullable = true)
    private LocalDateTime dateModified;
    
    @PrePersist
	protected void onCreate() {
    	 dateAdded = LocalDateTime.now();
    	 dateModified = LocalDateTime.now(); // This line is redundant if it's the same as dateAdded
    	 isActive = true;
	}
	
	@PreUpdate
	protected void onUpdate() {
		dateModified = LocalDateTime.now();
	}

}
