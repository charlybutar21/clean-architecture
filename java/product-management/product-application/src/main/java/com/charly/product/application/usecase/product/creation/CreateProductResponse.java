package com.charly.product.application.usecase.product.creation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductResponse {
    private String productCode;
}
