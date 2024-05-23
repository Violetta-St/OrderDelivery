package com.example.orderdelivery.AuthTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class GuestTests {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Главная\"")
    void GuestToMain() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Авторизация\"")
    void GuestToLogin() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }


    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Регистрация\"")
    void GuestToRegister() throws Exception{
        mockMvc.perform(get("/registration")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Товары\"")
    void GuestToProduct() throws Exception{
        mockMvc.perform(get("/product")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Категории товаров\"")
    void GuestToProductCategory() throws Exception{
        mockMvc.perform(get("/productCategory")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Заказы\"")
    void GuestToOrder() throws Exception{
        mockMvc.perform(get("/order")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithAnonymousUser
    @DisplayName(value = "Доступ гостя к странице \"Детали заказов\" ")
    void GuestToOrderDetail() throws Exception{
        mockMvc.perform(get("/orderDetail")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


}
