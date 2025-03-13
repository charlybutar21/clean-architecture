package com.charly.product.application.usecase.product.creation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long categoryId;
    private Long brandId;
}
