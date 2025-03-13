package com.charly.product.application.exception.product;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(Long id) {
        super("Brand with ID " + id + " not found");
    }
}
