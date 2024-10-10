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
public class UserUpdateIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testUserUpdate() throws Exception {
        // Step 1: Register a new user
        String email = "niva@example.com";
        String password = "password123";

        mockMvc.perform(post("/register")
                .param("name", "Niva Awale")
                .param("email", email)
                .param("password", password)
                .param("confirmPassword", password)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()); // Expect redirection after successful registration

        // Step 2: Update the user information
        mockMvc.perform(post("/update")
                .param("oldEmail", email)
                .param("newName", "Niva Updated")
                .param("newEmail", "niva.updated@example.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection()); // Expect redirection after successful update

        // Step 3: Verify that the user information has been updated in the database
        User updatedUser = userRepository.findByEmail("niva.updated@example.com");
        assert updatedUser != null; // Check that the user is not null
        assert updatedUser.getName().equals("Niva Updated"); // Check the name is updated
    }
}
