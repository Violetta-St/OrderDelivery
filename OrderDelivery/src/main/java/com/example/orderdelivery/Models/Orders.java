package com.example.orderdelivery.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, message = "Поле должно быть заполнено!")
    private String customer;
    @Size(min = 1, message = "Поле должно быть заполнено!")
    private String employee;
    @NotEmpty(message = "Дата не может быть пустой!")
    private String order_date;
    @NotEmpty(message = "Дата не может быть пустой!")
    private String ship_date;
    @Size(min = 1, message = "Поле должно быть заполнено!")
    private String ship_name;
    @Size(min = 1, message = "Поле должно быть заполнено!")
    private String ship_address;

    public String getOrderDesc(){
        return this.customer + " " + this.order_date;
    }
}
