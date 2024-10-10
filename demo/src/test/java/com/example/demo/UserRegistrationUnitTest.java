package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class UserRegistrationUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserRegistrationUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Given
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");

        // Mock the save behavior
        when(userRepository.save(user)).thenReturn(user);  // Mocking the save operation

        // When
        userService.saveUser(user);

        // Then
        verify(userRepository, times(1)).save(user);  // Verifies that the save method was called once
    }
}
