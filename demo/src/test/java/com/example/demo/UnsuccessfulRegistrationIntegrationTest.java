package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UnsuccessfulRegistrationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testUnsuccessfulRegistration() throws Exception {
        // Attempt registration with invalid data (e.g., password mismatch)
        mockMvc.perform(post("/register")
                .param("name", "Anu")
                .param("email", "invalidEmail") // Invalid email format
                .param("password", "password123")
                .param("confirmPassword", "differentPassword") // Mismatched password
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()) // Check if the status is OK
                .andExpect(model().attributeExists("errorMessage")) // Check if an error message exists
                .andExpect(view().name("register")); // Adjust the view name based on your setup
    }
}
