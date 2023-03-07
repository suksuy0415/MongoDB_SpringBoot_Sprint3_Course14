package com.niit.mongoDemo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.mongoDemo.domain.Product;
import com.niit.mongoDemo.helper.ProductNotFoundException;
import com.niit.mongoDemo.service.IProductService;
import com.niit.mongoDemo.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {


    private MockMvc mockMvc;


    @Mock
    private ProductServiceImpl productService;
    private Product product1, product2;

    List<Product> productList;


    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp(){

        product1 = new Product(11,"Cups","Glass cups","Present");
        product2 = new Product(10,"Mugs","Plastic Mugs","UnAvailable");

        productList = Arrays.asList(product1,product2);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

    }

    @AfterEach
    public void tearDown(){
        product1=null;
        product2 = null;
        productList=null;
    }


    @Test
    public void SaveProductSuccess() throws Exception {
      when(productService.addProduct(any())).thenReturn(product1);
        mockMvc.perform(post("/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).addProduct(any());



    }


    @Test
    public void getAllProductsAPI() throws Exception{

        when(productService.addProduct(any())).thenReturn(product1);
        mockMvc.perform(post("/addProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(MockMvcResultMatchers.jsonPath( "$.productCode").value(11));
              //  .andExpect(MockMvcResultMatchers.jsonPath( "$.Product").exists())
              //  .andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).addProduct(any());

        mockMvc.perform(MockMvcRequestBuilders.get("/Product")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
                // .andExpect(MockMvcResultMatchers.jsonPath( "$.productCode").value(11));
               // .andExpect(content().mimeType(IntegrationTestUtil.APPLICATION_JSON_UTF8))
               // .andExpect(jsonPath("$.", is(1)));
             //.andExpect(MockMvcResultMatchers.jsonPath( "$.Product").exists());
//          .andExpect(MockMvcResultMatchers.jsonPath("$.Product[*].productCode").isNotEmpty());

        verify(productService,times(1)).findallProducts();


    }



    @Test
    public void givenCustomerToSaveReturnSaveProductFailure() throws Exception {
       when(productService.getProduct(any())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/product/{productCode}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).getProduct(any());



    }


    @Test
    public void givenCustomerCodeDeleteCustomer() throws Exception {
      //when(productService.deleteProduct(any())).thenReturn(true);
        mockMvc.perform(delete("/product/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).deleteProduct(anyInt());

    }

    @Test
    public void DeleteNotPresentProduct() throws Exception {
       when(productService.getProduct(any())).thenThrow(ProductNotFoundException.class);
        mockMvc.perform(get("/product/{productCode}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).getProduct(any());

//        verify(productService,times(1)).deleteProduct(anyInt());

    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }
}

