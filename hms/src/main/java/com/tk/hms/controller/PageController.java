package com.tk.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
