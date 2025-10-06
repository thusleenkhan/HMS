package com.tk.hms.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tk.hms.model.User;
import com.tk.hms.repository.UserRepository;



@Service
public class UserService {
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo)
	{
		this.userRepo=userRepo;
	}
	@Transactional(readOnly = true)
	public boolean checkUserNameAndPassword(String userName,String password)
	{
		User user = userRepo.findByUsername(userName);

		if (user != null&& user.isActive() && user.getPassword().equals(password)) {
		    return true;
		} else {
		    return false;
		}

	}
	public User saveUser(User user)
	{
		System.out.println("save method in service");
		return userRepo.save(user);
	}

}
