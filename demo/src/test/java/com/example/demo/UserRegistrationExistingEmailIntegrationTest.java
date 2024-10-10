package com.example.demo;

import com.example.demo.entity.User;
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

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationExistingEmailIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll(); // Clear the database before each test

        // Register a test user to simulate existing email
        String email = "niva@example.com";
        User testUser = new User("Niva Awale", email, "password123");
        userRepository.save(testUser); // Save user to the database
    }

    @Test
    public void testUserRegistrationWithExistingEmail() throws Exception {
        // Attempt to register a new user with an existing email
        mockMvc.perform(post("/register")
                .param("name", "New User")
                .param("email", "niva@example.com") // Same email as the existing user
                .param("password", "password456")
                .param("confirmPassword", "password456")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()) // Expect to stay on the registration page
                .andExpect(model().attributeExists("errorMessage")) // Check for error message
                .andExpect(model().attribute("errorMessage", "Email already exists!")); // Check the specific error message
    }
}
