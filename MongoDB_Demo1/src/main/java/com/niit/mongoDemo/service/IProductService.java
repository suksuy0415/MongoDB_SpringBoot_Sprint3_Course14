package com.niit.mongoDemo.service;

import com.niit.mongoDemo.domain.Product;

import java.util.List;

public interface IProductService {

    Product addProduct(Product product);
    List<Product> findallProducts();
    Product getProduct(int productCode);
    boolean deleteProduct(int productCode);

    List<Product> findProductsInStock();
}
