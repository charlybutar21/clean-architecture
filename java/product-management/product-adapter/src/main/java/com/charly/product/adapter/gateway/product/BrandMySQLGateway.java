package com.charly.product.adapter.gateway.product;

import com.charly.product.adapter.repository.product.BrandRepository;
import com.charly.product.application.exception.product.BrandNotFoundException;
import com.charly.product.application.gateway.product.BrandGateway;
import com.charly.product.entity.model.product.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandMySQLGateway implements BrandGateway {

    private final BrandRepository brandRepository;

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
    }
}
