package com.example.orderdelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTests {
    @Autowired private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    void mainController() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Main"))
                .andExpect(content().string(containsString("Главная страница")));
    }

    @Test
    @WithMockUser
    void productCategoryController() throws Exception{
        mockMvc.perform(get("/ProductCategory"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductCategory/ProductCategory"))
                .andExpect(content().string(containsString("Категории товаров")));
    }

    @Test
    @WithMockUser
    void productController() throws Exception{
        mockMvc.perform(get("/Product"))
                .andExpect(status().isOk())
                .andExpect(view().name("Product/Product"))
                .andExpect(content().string(containsString("Товары")));
    }

    @Test
    @WithMockUser
    void ordersController() throws Exception{
        mockMvc.perform(get("/Order"))
                .andExpect(status().isOk())
                .andExpect(view().name("Order/Order"))
                .andExpect(content().string(containsString("Заказы")));
    }

    @Test
    @WithMockUser
    void orderDetailController() throws Exception{
        mockMvc.perform(get("/OrderDetail"))
                .andExpect(status().isOk())
                .andExpect(view().name("OrderDetail/OrderDetail"))
                .andExpect(content().string(containsString("Детали заказов")));
    }

    @Test
    @WithAnonymousUser
    void registrationController() throws Exception{
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("Registration"))
                .andExpect(content().string(containsString("Регистрация")));
    }

    @Test
    @WithAnonymousUser
    void loginController() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content().string(containsString("Вход")));
    }
}
