package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserRegistrationEmailAlreadyExistsUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserRegistrationEmailAlreadyExistsUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUserWithExistingEmail() {
        // Given
        String existingEmail = "nivaawale@gmail.com";
        User existingUser = new User();
        existingUser.setEmail(existingEmail);

        // Mocking the repository method to return an existing user
        when(userRepository.findByEmail(existingEmail)).thenReturn(existingUser);

        // Creating a new user with the same email
        User newUser = new User();
        newUser.setEmail(existingEmail);
        newUser.setName("New User");

        // When & Then
        // Assert that a RuntimeException is thrown when trying to register
        assertThrows(RuntimeException.class, () -> userService.saveUser(newUser), 
            "Expected to throw an exception when registering with an existing email.");
    }
}
