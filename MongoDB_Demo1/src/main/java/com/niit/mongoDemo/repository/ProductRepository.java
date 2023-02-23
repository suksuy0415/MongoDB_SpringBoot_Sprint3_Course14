package com.niit.mongoDemo.repository;



import com.niit.mongoDemo.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,Integer> {


    @Query("{productStock:{$in:[Present]}}")
    List<Product> findProductsInStock();

}
