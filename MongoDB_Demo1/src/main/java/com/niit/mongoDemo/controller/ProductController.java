package com.niit.mongoDemo.controller;


import com.niit.mongoDemo.domain.Product;
import com.niit.mongoDemo.helper.ProductNotFoundException;
import com.niit.mongoDemo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    ProductServiceImpl productService;

    @Autowired
    public  ProductController(ProductServiceImpl productService){

        this.productService=productService;

    }

    @GetMapping("/Product")
    public ResponseEntity<?> getAllProduct()
    {
        List<Product> customerList=productService.findallProducts();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product productdata)
    {
        Product product=productService.addProduct(productdata);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product/{productCode}")
    public ResponseEntity<?> getProdict(@PathVariable int productCode) throws ProductNotFoundException {
        Product fetchedProduct=null;
        ResponseEntity responseEntity=null;
        try {
            fetchedProduct = productService.getProduct(productCode);

            return   responseEntity=new ResponseEntity<>("given data found", HttpStatus.OK);
        } catch (NoSuchElementException e1) {
            throw new ProductNotFoundException();

        }

    }
    @DeleteMapping("/product/{productCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productCode)
    {
        productService.deleteProduct(productCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/productStock")
    public ResponseEntity<?> getAvailableProduct()
    {
        List<Product> fetchProduct=productService.findProductsInStock();
        return new ResponseEntity<>(fetchProduct, HttpStatus.OK);
    }


}
