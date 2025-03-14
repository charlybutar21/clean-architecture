package com.charly.product.adapter.gateway.product;

import com.charly.product.application.exception.product.CategoryNotFoundException;
import com.charly.product.application.gateway.product.CategoryGateway;
import com.charly.product.entity.model.product.Category;
import com.charly.product.adapter.repository.product.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
