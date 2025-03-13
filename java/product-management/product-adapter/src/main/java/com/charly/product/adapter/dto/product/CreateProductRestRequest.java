package com.charly.product.adapter.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRestRequest {
    @NotBlank(message = "Product code is required")
    private String code;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;

    @Min(value = 0, message = "Stock quantity must be at least 1")
    private Integer stockQuantity;

    @NotNull(message = "Category id is required")
    private Long categoryId;

    @NotNull(message = "Brand id is required")
    private Long brandId;
}
