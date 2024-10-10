import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UnsuccessfulUserUpdateIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User existingUser;

    @BeforeEach
    public void setUp() {
        // Set up a user that exists in the database
        existingUser = new User();
        existingUser.setName("Existing User");
        existingUser.setEmail("existing@example.com");
        userRepository.save(existingUser);
    }

    @Test
    public void testUnsuccessfulUserUpdate() throws Exception {
        // Attempt to update a non-existent user by providing an incorrect oldEmail
        mockMvc.perform(post("/update")
                .param("oldEmail", "nonexistent@example.com") // Invalid email
                .param("newName", "New Name")
                .param("newEmail", "newemail@example.com")
        )
                .andExpect(status().isOk()) // Expecting the request to go through
                .andExpect(model().attributeExists("errorMessage")) // Error message should be present
                .andExpect(view().name("staff-edit")); // Should return to the same view
    }
}
