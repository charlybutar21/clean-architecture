package com.charly.product.application.usecase.product.list;

public interface ListProductInputBoundary {
    void execute(ListProductRequest request, ListProductOutputBoundary presenter);
}
