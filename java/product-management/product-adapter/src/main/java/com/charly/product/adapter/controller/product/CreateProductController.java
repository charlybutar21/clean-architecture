package com.charly.product.adapter.controller.product;

import com.charly.product.adapter.dto.product.CreateProductRestRequest;
import com.charly.product.adapter.dto.product.CreateProductRestResponse;
import com.charly.product.adapter.presenter.product.CreateProductPresenter;
import com.charly.product.application.usecase.product.creation.CreateProductInputBoundary;
import com.charly.product.application.usecase.product.creation.CreateProductRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class CreateProductController {

    private final CreateProductInputBoundary usecase;
    private final CreateProductPresenter presenter;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateProductRestResponse> createProduct(@RequestBody @Valid CreateProductRestRequest restRequest) {
        CreateProductRequest request = modelMapper.map(restRequest, CreateProductRequest.class);

        usecase.execute(request, presenter);

        return ResponseEntity.status(HttpStatus.CREATED).body(presenter.getRestResponse());
    }
}
