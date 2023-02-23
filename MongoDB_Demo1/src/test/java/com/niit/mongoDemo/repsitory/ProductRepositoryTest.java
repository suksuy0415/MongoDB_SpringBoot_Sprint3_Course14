package com.niit.mongoDemo.repsitory;

import com.niit.mongoDemo.domain.Product;
import com.niit.mongoDemo.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;
    private Product product;


    @BeforeEach
    void setUp()
    {
        product=new Product(4,"Laptop","Dell Products","Present");

    }


    @AfterEach
    void tearDown() {

        product = null;
        productRepository.deleteAll(); // clear only dummy rows not all the rows

    }


    @Test
    @DisplayName("Test case for saving Product object")
    void givenCustomerToSaveShouldReturnCustomer() {
        productRepository.save(product);
        Product product1 = productRepository.findById(product.getProductCode()).get();
        assertNotNull(product1);
        assertEquals(product.getProductCode(), product1.getProductCode());
    }


    @Test
    @DisplayName("Test case for deleting product object")
    public void givenCustomerToDeleteShouldDeleteCustomer() {
        productRepository.insert(product);
        Product product12 = productRepository.findById(product.getProductCode()).get();
        productRepository.delete(product12);
        assertEquals(Optional.empty(), productRepository.findById(product12.getProductCode()));

    }



    @Test
    @DisplayName("Test case for retrieving all the  Product object")
    public void givenTrackReturnGetAllTrack() {

        productRepository.insert(product);

        Product product11 = new Product(109, "Chair","wooden chairs" , "Unavailable");
        productRepository.insert(product11);

        List<Product> list = productRepository.findAll();
        assertEquals(2, list.size());
        assertEquals("Chair", list.get(1).getProductName());

    }


    @Test
    @DisplayName("Test case for retrieving all the  Available Product object")
    public void getStockPresent() {

        productRepository.insert(product);

        Product product11 = new Product(109, "Chair","wooden chairs" , "Present");
        productRepository.insert(product11);
        Product product12 = new Product(110, "Fridge","Samsung" , "Unavailable");
        productRepository.insert(product12);

        List<Product> list = productRepository.findProductsInStock();
        assertEquals(2, list.size());
        assertEquals("Chair", list.get(1).getProductName());

    }





}
