package com.tk.hms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mock;
//import org.springframework.boot.test.mock.mockito.*;

import com.tk.hms.controller.LoginController;
import com.tk.hms.service.UserService;

@WebMvcTest(LoginController.class)
@Import(SecurityConfig.class) 
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @WithMockUser(username = "test1", roles = "USER")
    public void testHandleLogin_Successful() throws Exception {
        // Arrange: Mock userService to return true (successful login)
        Mockito.when(userService.checkUserNameAndPassword("test1", "test1")).thenReturn(true);

        // Act & Assert: Perform POST request to /login and expect a redirect to /dashboard
        mockMvc.perform(post("/login")
                .param("username", "test1")
                .param("password", "test1"))
                .andExpect(status().is3xxRedirection())  // 3xx is for redirect status
                .andExpect(redirectedUrl("/dashboard"));  // Expecting the redirection to /dashboard
    }

    @Test
    public void testHandleLogin_Failure() throws Exception {
        // Arrange: Mock userService to return false (failed login)
        Mockito.when(userService.checkUserNameAndPassword("invalidUser", "invalidPass")).thenReturn(false);

        // Act & Assert: Perform POST request to /login and expect to stay on the login page with an error message
        mockMvc.perform(post("/login")
                .param("username", "invalidUser")
                .param("password", "invalidPass"))
                .andExpect(status().isOk())  // Expecting HTTP 200 (OK), because we're returning to the same page
                .andExpect(view().name("login"))  // Expecting the "login" view to be rendered
                .andExpect(model().attributeExists("error"))  // Ensure the "error" attribute is in the model
                .andExpect(model().attribute("error", "Invalid username or password"));  // Verify the error message
    }
}

