package com.charly.product.application.dto.product;

import com.charly.product.entity.model.product.Brand;
import com.charly.product.entity.model.product.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductEntityRequest {
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long categoryId;
    private Long brandId;
    private Category category;
    private Brand brand;
}
