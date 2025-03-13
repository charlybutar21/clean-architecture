package com.charly.product.adapter.presenter.product;

import com.charly.product.adapter.dto.product.ListProductRestResponse;
import com.charly.product.application.usecase.product.list.ListProductOutputBoundary;
import com.charly.product.application.usecase.product.list.ListProductResponse;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ListProductPresenter implements ListProductOutputBoundary {

    private ListProductRestResponse restResponse;

    private final ModelMapper modelMapper;

    public ListProductPresenter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void present(ListProductResponse response) {
        restResponse = modelMapper.map(response, ListProductRestResponse.class);
    }
}
