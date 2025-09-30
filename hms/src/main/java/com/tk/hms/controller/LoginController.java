package com.tk.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Authentication logic (for example purposes)
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/dashboard";  // Redirect to dashboard if successful
        } else {
            // Add error message to model
            model.addAttribute("error", "Invalid username or password");
            return "login";  // Return to the login page with error message
        }
    }

}
