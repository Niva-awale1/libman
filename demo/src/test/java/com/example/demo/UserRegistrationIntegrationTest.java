package com.example.demo;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserRegistrationIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testRegisterUser() {
        User user = new User("Test User", "test@example.com", "password123");
        HttpEntity<User> request = new HttpEntity<>(user);

        ResponseEntity<String> response = restTemplate.postForEntity("/register", request, String.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertNotNull(userService.findByEmail("test@example.com"));
    }

    @Test
    public void testLoginUser() {
        User user = new User("Test User", "test@example.com", passwordEncoder.encode("password123"));
        userService.saveUser(user);

        ResponseEntity<String> response = restTemplate.postForEntity("/login?email=test@example.com&password=password123", null, String.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().contains("/home"));
    }
}