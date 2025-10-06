package com.tk.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.tk.hms.model.*;
import com.tk.hms.service.*;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService)
	{
		this.userService= userService;
	}
	
	@PostMapping("/user/save")
	public String saveUser(@Valid @ModelAttribute User user,BindingResult result,
            Model model)
	{
		System.out.println("save method");
		if (result.hasErrors()) {            
            return "user-master";
        }
		if(userService.saveUser(user).getId()!=null)
		{
			System.out.println("saved");
			return "login";
		}
		else {
			System.out.println("not saved");
			return "user-master";
		}
		
	}

}
