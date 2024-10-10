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

public class UserSaveUnsuccessfulUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserSaveUnsuccessfulUnitTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUserWithoutEmail() {
        // Given
        User userWithoutEmail = new User();
        userWithoutEmail.setName("Test User Without Email");

        // Mocking the repository to simulate save failure
        when(userRepository.save(userWithoutEmail)).thenThrow(new RuntimeException("Email is mandatory"));

        // When & Then
        // Assert that a RuntimeException is thrown when trying to save a user without an email
        assertThrows(RuntimeException.class, () -> userService.saveUser(userWithoutEmail), 
            "Expected to throw an exception when saving a user without an email.");
    }
}
