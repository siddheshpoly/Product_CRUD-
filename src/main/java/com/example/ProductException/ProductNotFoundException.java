package com.example.ProductException;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId, String message) {
        super();

    }
}
