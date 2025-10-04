package com.tk.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tk.hms.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByUsername(String userName);

}
