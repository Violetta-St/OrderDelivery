package com.example.orderdelivery.ModelsTest;

import com.example.orderdelivery.Models.Product;
import com.example.orderdelivery.Models.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductTests {
    @InjectMocks
    private Product product;

    private ProductCategory productCategory;

    @BeforeEach
    void setProduct() {
        product = Product.builder()
                .id(1L)
                .productCategory(productCategory)
                .product_name("Стиральная машина")
                .unit_price(24500)
                .in_stock(10)
                .in_order(1)
                .build();
    }

    @Test
    void productId() {
        assertEquals(1L, product.getId());
    }

    @Test
    void productCategoryKey() {
        assertEquals(productCategory, product.getProductCategory());
    }

    @Test
    void productUnitPrice() {
        assertEquals(24500, product.getUnit_price());
    }

    @Test
    void productInStock() {
        assertEquals(10, product.getIn_stock());
    }

    @Test
    void productInOrder() {
        assertEquals(1, product.getIn_order());
    }
}
