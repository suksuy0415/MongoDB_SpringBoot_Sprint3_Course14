package com.niit.mongoDemo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {


    @Id
    int productCode;
    String productName;
    String productDescription;
    String productStock;


    public Product() {

    }

    public Product(int productCode, String productName, String productDescription, String productStock) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productStock = productStock;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productStock='" + productStock + '\'' +
                '}';
    }
}
