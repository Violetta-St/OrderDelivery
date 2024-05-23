package com.example.orderdelivery.Repositories;

import com.example.orderdelivery.Models.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {}
