package com.charly.product.application.gateway.product;

import com.charly.product.entity.model.product.Category;

public interface CategoryGateway {
    Category findById(Long id);
}
