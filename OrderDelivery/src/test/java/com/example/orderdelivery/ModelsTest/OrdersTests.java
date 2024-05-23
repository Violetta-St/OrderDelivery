package com.example.orderdelivery.ModelsTest;

import com.example.orderdelivery.Models.Orders;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrdersTests {
    @InjectMocks
    private Orders orders;

    @BeforeEach
    void setOrders() {
        orders = Orders.builder()
                .id(1L)
                .customer("Панфилов Валерий Юрьевич")
                .employee("Степаненко Виолетта")
                .order_date("12.05.2024")
                .ship_date("14.05.2024")
                .ship_name("СДЭК")
                .ship_address("ул.Большая Садовая 69")
                .build();
    }

    @Test
    void ordersId() {
        assertEquals(1L, orders.getId());
    }

    @Test
    void ordersCustomer() {
        assertEquals("Панфилов Валерий Юрьевич", orders.getCustomer());
    }

    @Test
    void ordersEmployee() {
        assertEquals("Степаненко Виолетта", orders.getEmployee());
    }

    @Test
    void ordersOrderDate() {
        assertEquals("12.05.2024", orders.getOrder_date());
    }

    @Test
    void ordersShipDate() {
        assertEquals("14.05.2024", orders.getShip_date());
    }

    @Test
    void ordersShipName() {
        assertEquals("СДЭК", orders.getShip_name());
    }

    @Test
    void ordersShipAdress() {
        assertEquals("ул.Большая Садовая 69", orders.getShip_address());
    }

    @Test
    void ordersGetOrderDesc() {
        assertEquals("Панфилов Валерий Юрьевич 12.05.2024", orders.getOrderDesc());
    }
}
