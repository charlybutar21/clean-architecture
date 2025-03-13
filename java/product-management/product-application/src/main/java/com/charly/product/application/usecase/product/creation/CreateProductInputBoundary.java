package com.charly.product.application.usecase.product.creation;

public interface CreateProductInputBoundary {
    void execute(CreateProductRequest request, CreateProductOutputBoundary presenter);
}
