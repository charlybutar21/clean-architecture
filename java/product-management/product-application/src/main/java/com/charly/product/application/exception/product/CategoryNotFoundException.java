package com.charly.product.application.exception.product;

public class CategoryNotFoundException extends RuntimeException  {
    public CategoryNotFoundException(Long id) {
        super("Category with ID " + id + " not found");
    }
}
