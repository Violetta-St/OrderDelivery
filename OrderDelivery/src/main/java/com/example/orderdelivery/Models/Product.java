package com.example.orderdelivery.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, message = "Поле не может быть пустым!")
    private String product_name;
    @Min(value = 1, message = "Поле не может быть пустым!")
    private float unit_price;
    @Min(value = 1, message = "Поле не может быть пустым!")
    private int in_stock;
    @Min(value = 1, message = "Поле не может быть пустым!")
    private int in_order;
    @ManyToOne
    @NotNull(message = "Необходимо выбрать категорию товаров")
    private ProductCategory productCategory;
}
