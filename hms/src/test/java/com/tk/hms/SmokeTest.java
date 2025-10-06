package com.tk.hms;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tk.hms.controller.*;
import com.tk.hms.service.*;

@SpringBootTest
public class SmokeTest {
	@Autowired
	private PageController pageController; 
	@Autowired
	private LoginController loginController; 
	@Autowired
	private UserController userController; 
	@Autowired
	private UserService userService;
	@Test
	void contextLoads() throws Exception{
		assertThat(pageController).isNotNull();
		assertThat(loginController).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(userService).isNotNull();
		
	}

}
