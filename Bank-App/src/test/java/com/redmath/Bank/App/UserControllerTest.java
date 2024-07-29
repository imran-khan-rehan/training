package com.redmath.Bank.App;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.redmath.Bank.App.User.User;
//import com.redmath.Bank.App.User.UserController;
import com.redmath.Bank.App.User.UserService;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Order;
//import java.util.Collections;
//import java.util.Optional;

import static org.mockito.Mockito.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
    @Order(1)
    @Test
    void shouldReturnAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    @Order(2)
    @Test
    public void testGetUserDetails() throws Exception {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setName("Test User");
        when(userService.findUser(1L)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test User"));
    }

//    @Test
//    public void testUpdateUser() throws Exception {
//        // Arrange
//        User existingUser = new User();
//        existingUser.setId(1L);
//        existingUser.setEmail("test@example.com");
//        existingUser.setName("Test User");
//        User updatedUser = new User();
//        updatedUser.setId(1L);
//        updatedUser.setEmail("updated@example.com");
//        updatedUser.setName("Updated User");
//        when(userService.findUser(1L)).thenReturn(existingUser);
//        when(userService.alreadyRegister("updated@example.com")).thenReturn(false);
//        when(userService.save(any(User.class))).thenReturn(updatedUser);
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//
//        // Act & Assert
//        mockMvc.perform(put("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedUser)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("updated@example.com"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated User"));
//    }
//
//    @Test
//    public void testUpdateUserNotFound() throws Exception {
//        // Arrange
//        User updatedUser = new User();
//        updatedUser.setId(1L);
//        updatedUser.setEmail("updated@example.com");
//        updatedUser.setName("Updated User");
//        when(userService.findUser(1L)).thenReturn(null);
//
//        // Act & Assert
//        mockMvc.perform(put("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedUser)))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("User not found"));
//    }
//
//    @Test
//    public void testDeleteUser() throws Exception {
//        // Arrange
//        User existingUser = new User();
//        existingUser.setId(1L);
//        when(userService.findUser(1L)).thenReturn(existingUser);
//
//        // Act & Assert
//        mockMvc.perform(delete("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void testDeleteUserNotFound() throws Exception {
//        // Arrange
//        when(userService.findUser(1L)).thenReturn(null);
//
//        // Act & Assert
//        mockMvc.perform(delete("/api/v1/users/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
}
