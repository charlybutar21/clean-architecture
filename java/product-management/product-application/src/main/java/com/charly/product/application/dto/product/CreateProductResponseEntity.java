package com.charly.product.application.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductResponseEntity {
    private String productCode;
}
