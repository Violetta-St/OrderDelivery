package com.example.orderdelivery.ModelsTest;

import com.example.orderdelivery.Models.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductCategoryTests {
    @InjectMocks
    private ProductCategory productCategory;

    @BeforeEach
    void setProductCategory() {
        productCategory = ProductCategory.builder()
                .id(1L)
                .category_name("Бытовая техника")
                .description("Все для дома и дачи")
                .build();
    }

    @Test
    void productCategoryId() {
        assertEquals(1L, productCategory.getId());
    }

    @Test
    void productCategoryName() {
        assertEquals("Бытовая техника", productCategory.getCategory_name());
    }

    @Test
    void productCategoryDescription() {
        assertEquals("Все для дома и дачи", productCategory.getDescription());
    }


}
