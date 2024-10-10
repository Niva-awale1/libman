package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindingUserByEmailUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public FindingUserByEmailUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByEmail() {
        // Given
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        
        // Mocking the behavior of userRepository
        when(userRepository.findByEmail(email)).thenReturn(user);

        // When
        User result = userService.findByEmail(email);

        // Then
        assertEquals(email, result.getEmail());
    }
}
