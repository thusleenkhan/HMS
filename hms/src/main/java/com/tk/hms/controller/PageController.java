package com.tk.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tk.hms.model.User;

@Controller
public class PageController {
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	@GetMapping("/dashboard")
	public String dashboard()
	{
		return "under-construction";
	}
	@GetMapping("/user")
	public String user(Model model)
	{
		model.addAttribute("user",new User());
		return "user-master";
	}

}
