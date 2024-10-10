package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserDeletionUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserDeletionUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteUser() {
        // Given
        String email = "deleteuser@example.com";
        User user = new User();
        user.setEmail(email);
        user.setName("User to Delete");

        when(userRepository.findByEmail(email)).thenReturn(user); // Mock user retrieval

        // When
        userService.deleteUserByEmail(email); // Call the delete method

        // Then
        verify(userRepository).delete(user); // Verify that the delete method was called
    }
}
