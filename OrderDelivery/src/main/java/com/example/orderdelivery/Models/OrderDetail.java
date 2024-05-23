package com.example.orderdelivery.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;
    @Size(min = 1, message = "Поле не должно быть пустым!")
    private String discount;
    @ManyToOne
    @NotNull(message = "Необходимо выбрать товар")
    private Product product;
    @ManyToOne
    @NotNull(message = "Необходимо выбрать заказ")
    private Orders orders;

}
