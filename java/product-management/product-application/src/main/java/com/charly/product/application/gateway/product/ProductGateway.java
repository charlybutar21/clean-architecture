package com.charly.product.application.gateway.product;

import com.charly.product.application.dto.product.CreateProductEntityRequest;
import com.charly.product.application.dto.product.ListProductEntityRequest;
import com.charly.product.application.dto.product.CreateProductResponseEntity;
import com.charly.product.application.dto.product.ListProductResponseEntity;

public interface ProductGateway {
    CreateProductResponseEntity save(CreateProductEntityRequest entityRequest);
    ListProductResponseEntity search(ListProductEntityRequest entityRequest);
}
