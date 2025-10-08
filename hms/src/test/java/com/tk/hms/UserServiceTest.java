package com.tk.hms;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tk.hms.model.User;
import com.tk.hms.repository.UserRepository;
import com.tk.hms.service.UserService;

public class UserServiceTest {
	 	@Mock
	    private UserRepository userRepo;  // dependency mock

	    private UserService userService;  // class under test

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);  // initialize mocks
	        userService = new UserService(userRepo);
	    }

	    // ✅ Case 1: valid username + password + active user
	    @Test
	    void testCheckUserNameAndPassword_ValidUser() {
	        User user = new User();
	        user.setUsername("john");
	        user.setPassword("1234");
	        user.setActive(true);

	        when(userRepo.findByUsername("john")).thenReturn(user);

	        boolean result = userService.checkUserNameAndPassword("john", "1234");

	        assertTrue(result, "Expected true for valid username/password");
	        verify(userRepo, times(1)).findByUsername("john");
	    }

	    // ✅ Case 2: invalid password
	    @Test
	    void testCheckUserNameAndPassword_InvalidPassword() {
	        User user = new User();
	        user.setUsername("john");
	        user.setPassword("abcd");
	        user.setActive(true);

	        when(userRepo.findByUsername("john")).thenReturn(user);

	        boolean result = userService.checkUserNameAndPassword("john", "wrong");

	        assertFalse(result, "Expected false for wrong password");
	        verify(userRepo, times(1)).findByUsername("john");
	    }

	    // ✅ Case 3: user not found
	    @Test
	    void testCheckUserNameAndPassword_UserNotFound() {
	        when(userRepo.findByUsername("unknown")).thenReturn(null);

	        boolean result = userService.checkUserNameAndPassword("unknown", "1234");

	        assertFalse(result, "Expected false when user not found");
	        verify(userRepo, times(1)).findByUsername("unknown");
	    }

	    // ✅ Case 4: user found but inactive
	    @Test
	    void testCheckUserNameAndPassword_UserInactive() {
	        User user = new User();
	        user.setUsername("john");
	        user.setPassword("1234");
	        user.setActive(false);

	        when(userRepo.findByUsername("john")).thenReturn(user);

	        boolean result = userService.checkUserNameAndPassword("john", "1234");

	        assertFalse(result, "Expected false for inactive user");
	        verify(userRepo, times(1)).findByUsername("john");
	    }

	    // ✅ Case 5: saveUser() method should call repo.save()
	    @Test
	    void testSaveUser() {
	        User user = new User();
	        user.setUsername("john");
	        user.setPassword("1234");

	        when(userRepo.save(user)).thenReturn(user);

	        User savedUser = userService.saveUser(user);

	        assertNotNull(savedUser, "Saved user should not be null");
	        assertEquals("john", savedUser.getUsername());
	        verify(userRepo, times(1)).save(user);
	    }

}


