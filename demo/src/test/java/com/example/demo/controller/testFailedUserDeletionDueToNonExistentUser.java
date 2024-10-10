package com.example.demo.controller;

import com.example.demo.controller.HomeController;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class testFailedUserDeletionDueToNonExistentUser {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFailedUserDeletionDueToNonExistentUser() {
        // Arrange
        String email = "nonexistent@example.com";  // Non-existent user email

        // Mock the behavior of the UserService to throw an exception when trying to delete the user
        doThrow(new RuntimeException("User not found")).when(userService).deleteUserByEmail(email);

        // Act
        String viewName = homeController.deleteUser(email);

        // Assert
        assertEquals("redirect:/users", viewName);  // Expect to be redirected to the users list page
        // The test fails because an exception is thrown for a non-existent user
    }
}
