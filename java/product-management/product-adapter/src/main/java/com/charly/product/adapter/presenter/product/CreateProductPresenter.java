package com.charly.product.adapter.presenter.product;

import com.charly.product.adapter.dto.product.CreateProductRestResponse;
import com.charly.product.application.usecase.product.creation.CreateProductOutputBoundary;
import com.charly.product.application.usecase.product.creation.CreateProductResponse;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CreateProductPresenter implements CreateProductOutputBoundary {

    private CreateProductRestResponse restResponse;

    private final ModelMapper modelMapper;

    public CreateProductPresenter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void present(CreateProductResponse response) {
        restResponse = modelMapper.map(response, CreateProductRestResponse.class);
    }
}
