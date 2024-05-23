package com.example.orderdelivery.ModelsTest;

import com.example.orderdelivery.Models.OrderDetail;
import com.example.orderdelivery.Models.Orders;
import com.example.orderdelivery.Models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderDetailTests {
    @InjectMocks
    private OrderDetail orderDetail;

    private Orders orders;

    private Product product;

    @BeforeEach
    void setOrderDetail() {
        orderDetail = OrderDetail.builder()
                .id(1L)
                .orders(orders)
                .product(product)
                .price(24500)
                .discount("10%")
                .build();
    }

    @Test
    void orderDetailId() {
        assertEquals(1L, orderDetail.getId());
    }

    @Test
    void orderDetailOrders() {
        assertEquals(orders, orderDetail.getOrders());
    }

    @Test
    void orderDetailProducts() {
        assertEquals(product, orderDetail.getProduct());
    }

    @Test
    void orderDetailPrice() {
        assertEquals(24500, orderDetail.getPrice());
    }

    @Test
    void orderDetailDiscount() {
        assertEquals("10%", orderDetail.getDiscount());
    }
}
