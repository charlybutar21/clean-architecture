package com.charly.product.adapter.controller.product;

import com.charly.product.adapter.dto.product.ListProductRestRequest;
import com.charly.product.adapter.dto.product.ListProductRestResponse;
import com.charly.product.adapter.presenter.product.ListProductPresenter;
import com.charly.product.application.usecase.product.list.ListProductInputBoundary;
import com.charly.product.application.usecase.product.list.ListProductRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ListProductController {
    private final ListProductInputBoundary usecase;
    private final ListProductPresenter presenter;
    private final ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListProductRestResponse> searchProducts(ListProductRestRequest restRequest) {
        ListProductRequest request = modelMapper.map(restRequest, ListProductRequest.class);

        usecase.execute(request, presenter);

        return ResponseEntity.ok(presenter.getRestResponse());
    }
}
