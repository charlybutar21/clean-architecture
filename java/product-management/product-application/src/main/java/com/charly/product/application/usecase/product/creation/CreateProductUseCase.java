package com.charly.product.application.usecase.product.creation;

import com.charly.product.application.dto.product.CreateProductEntityRequest;
import com.charly.product.application.dto.product.CreateProductResponseEntity;
import com.charly.product.application.gateway.product.ProductEventGateway;
import com.charly.product.application.gateway.product.ProductGateway;
import com.charly.product.application.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@AllArgsConstructor
public class CreateProductUseCase implements CreateProductInputBoundary{

    private final ProductEventGateway productEventGateway;
    private final ProductGateway productGateway;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void execute(CreateProductRequest request, CreateProductOutputBoundary presenter) {
        CreateProductEntityRequest createProductEntityRequest = modelMapper.map(request, CreateProductEntityRequest.class);

        CreateProductResponseEntity createProductResponseEntity = productGateway.save(createProductEntityRequest);

        log.info("Product created with code {}", request.getCode());

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                String productEventJson = JsonUtil.toJson(createProductEntityRequest);
                log.info("Transaction committed, sending Kafka event: {}", productEventJson);
                productEventGateway.publishProductCreatedEvent(productEventJson);
            }
        });

        CreateProductResponse response = modelMapper.map(createProductResponseEntity, CreateProductResponse.class);
        presenter.present(response);
    }
}
