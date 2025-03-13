package com.charly.product.application.gateway.product;

import com.charly.product.entity.model.product.Brand;

public interface BrandGateway {
    Brand findById(Long id);
}
