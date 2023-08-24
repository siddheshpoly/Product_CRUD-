package com.example.ProductModel;

import jakarta.persistence.*;


@Entity
@Table(name = "ProductData")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProductId;
    private String ProductName;
    private String ProductDescription;
    private Float ProductPrice;

    public Product(){
        super();
    }

    public Product(Long productId, String productName, String productDescription, Float productPrice) {
        ProductId = productId;
        ProductName = productName;
        ProductDescription = productDescription;
        ProductPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", ProductName='" + ProductName + '\'' +
                ", ProductDescription='" + ProductDescription + '\'' +
                ", ProductPrice=" + ProductPrice +
                '}';
    }

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public Float getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Float productPrice) {
        ProductPrice = productPrice;
    }
}
