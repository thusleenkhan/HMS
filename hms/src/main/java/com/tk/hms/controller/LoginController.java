package com.tk.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tk.hms.service.UserService;

@Controller
public class LoginController {
	private final UserService userService;
	public LoginController(UserService userService)
	{
		this.userService= userService;
	}
	@PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Authentication logic (for example purposes)
        if (userService.checkUserNameAndPassword(username, password)) {
        	System.out.println("handle login"+username+","+password);
            return "redirect:/dashboard";  // Redirect to dashboard if successful
        } else {
            // Add error message to model
            model.addAttribute("error", "Invalid username or password");
            return "login";  // Return to the login page with error message
        }
    }

}
