package com.example.orderdelivery.AuthTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserTests {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Главная\"")
    void UserToMain() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Товары\"")
    void UserToProduct() throws Exception{
        mockMvc.perform(get("/product")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Категории товаров\"")
    void UserToProductCategory() throws Exception{
        mockMvc.perform(get("/productCategory")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Заказы\"")
    void UserToOrder() throws Exception{
        mockMvc.perform(get("/order")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Детали заказов\"")
    void UserToOrderDetail() throws Exception{
        mockMvc.perform(get("/orderDetail")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Авторизация\"")
    void UserToLogin() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    @DisplayName(value = "Доступ пользователя к странице \"Регистрация\"")
    void UserToRegister() throws Exception{
        mockMvc.perform(get("/registration")).andExpect(status().isForbidden());
    }
}
