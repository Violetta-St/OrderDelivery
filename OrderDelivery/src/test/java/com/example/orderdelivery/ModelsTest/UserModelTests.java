package com.example.orderdelivery.ModelsTest;

import com.example.orderdelivery.Models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserModelTests {

    @InjectMocks
    private UserModel user;

    @BeforeEach
    void setUserModel() {
        user = UserModel.builder()
                .id(1L)
                .username("Виолетта")
                .password("1234567890")
                .email("violetta@gmail.com")
                .build();
    }

    @Test
    void userId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void userUsername() {
        assertEquals("Виолетта", user.getUsername());
    }

    @Test
    void userPassword() {
        assertEquals("1234567890", user.getPassword());
    }

    @Test
    void userEmail() {
        assertEquals("violetta@gmail.com", user.getEmail());
    }
}
