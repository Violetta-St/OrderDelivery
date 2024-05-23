package com.example.orderdelivery.Repositories;

import com.example.orderdelivery.Models.Product;
import com.example.orderdelivery.Models.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> { }
