package com.niit.mongoDemo.service;

import com.niit.mongoDemo.domain.Product;
import com.niit.mongoDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements IProductService{



    ProductRepository productRepository;

    @Autowired
    public  ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findallProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(int productCode)  {



        return productRepository.findById(productCode).get();
    }

    @Override
    public boolean deleteProduct(int productCode) {
          productRepository.deleteById(productCode);
          return true;
    }

    @Override
    public List<Product> findProductsInStock() {
        return productRepository.findProductsInStock();
    }
}
