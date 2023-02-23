package com.niit.mongoDemo.service;


import com.niit.mongoDemo.domain.Product;
import com.niit.mongoDemo.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    private Product product1, product2;
    List<Product> productList;


    @BeforeEach
    void setUp() {
        product1 = new Product(11,"Cups","Glass cups","Present");
        product2 = new Product(10,"Mugs","Plastic Mugs","UnAvailable");

        productList = Arrays.asList(product1,product2);
    }

    @AfterEach
    void tearDown() {
        product1=null;
        product2 = null;
        productList=null;
    }


    @Test
    public void givenProductToSaveReturnSavedProductSuccess(){
        when(productRepository.save(any())).thenReturn(product1);
        assertEquals(product1,productService.addProduct(product1));

        verify(productRepository,times(1)).save(any());

    }




    @Test
    public void givenCustomerToDeleteShouldDeleteSuccess() {

        //when(productRepository.findById(product1.getProductCode())).thenReturn(Optional.ofNullable(product1));
        boolean flag = productService.deleteProduct(product1.getProductCode());
        assertEquals(true,flag);

        verify(productRepository,times(1)).deleteById(any());
        verify(productRepository,times(1)).deleteById(any());

    }


    @Test
    public void  getProductInStock(){
        when(productRepository.save(any())).thenReturn(product1);
        assertEquals(product1,productService.addProduct(product1));
        when(productRepository.findProductsInStock()).thenReturn((List<Product>)product1);
        //verify(productRepository,times(1)).findProductsInStock();

    }

}

