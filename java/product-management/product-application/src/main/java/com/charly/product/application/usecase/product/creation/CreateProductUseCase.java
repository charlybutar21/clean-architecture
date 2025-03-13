package com.charly.product.application.usecase.product.creation;

import com.charly.product.application.dto.product.CreateProductEntityRequest;
import com.charly.product.application.dto.product.CreateProductResponseEntity;
import com.charly.product.application.gateway.product.BrandGateway;
import com.charly.product.application.gateway.product.CategoryGateway;
import com.charly.product.application.gateway.product.ProductGateway;
import com.charly.product.entity.model.product.Brand;
import com.charly.product.entity.model.product.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CreateProductUseCase implements CreateProductInputBoundary{

    private final CategoryGateway categoryGateway;
    private final BrandGateway brandGateway;
    private final ProductGateway productGateway;
    private final ModelMapper modelMapper;

    @Override
    public void execute(CreateProductRequest request, CreateProductOutputBoundary presenter) {
        Category category = categoryGateway.findById(request.getCategoryId());

        Brand brand = brandGateway.findById(request.getBrandId());

        CreateProductEntityRequest createProductEntityRequest = modelMapper.map(request, CreateProductEntityRequest.class);
        createProductEntityRequest.setCategory(category);
        createProductEntityRequest.setBrand(brand);

        CreateProductResponseEntity createProductResponseEntity = productGateway.save(createProductEntityRequest);

        log.info("Product created with code {}", request.getCode());

        CreateProductResponse response = modelMapper.map(createProductResponseEntity, CreateProductResponse.class);
        presenter.present(response);
    }
}
