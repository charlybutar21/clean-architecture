package com.charly.product.application.usecase.product.list;

import com.charly.product.application.dto.product.ListProductEntityRequest;
import com.charly.product.application.dto.product.ListProductResponseEntity;
import com.charly.product.application.gateway.product.ProductGateway;
import com.charly.product.entity.model.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
@Slf4j
public class ListProductUseCase implements ListProductInputBoundary{
    private final ProductGateway productGateway;
    private final ModelMapper modelMapper;

    @Override
    public void execute(ListProductRequest request, ListProductOutputBoundary presenter) {
        ListProductEntityRequest listProductEntityRequest = modelMapper.map(request, ListProductEntityRequest.class);
        listProductEntityRequest.setStatuses(Collections.singletonList(ProductStatus.ACTIVE));

        ListProductResponseEntity listProductResponseEntity = productGateway.search(listProductEntityRequest);

        ListProductResponse response = modelMapper.map(listProductResponseEntity, ListProductResponse.class);

        presenter.present(response);
    }
}
