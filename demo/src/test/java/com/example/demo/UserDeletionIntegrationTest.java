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

@SpringBootTest
@AutoConfigureMockMvc
public class UserDeletionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testUserDeletion() throws Exception {
        // Step 1: Register a new user
        String email = "niva@example.com";
        String password = "password123";

        mockMvc.perform(post("/register")
                .param("name", "Niva Awale")
                .param("email", email)
                .param("password", password)
                .param("confirmPassword", password) // Ensure confirmation matches
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()); // Expect redirection after successful registration

        // Step 2: Delete the user
        mockMvc.perform(post("/delete")
                .param("email", email))
                .andExpect(status().is3xxRedirection()); // Expect redirection after successful deletion

        // Step 3: Verify that the user has been deleted from the database
        User deletedUser = userRepository.findByEmail(email);
        assert deletedUser == null; // Check that the user is null (deleted)
    }
}
