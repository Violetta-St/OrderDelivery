package com.example.orderdelivery.Repositories;

import com.example.orderdelivery.Models.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {}
