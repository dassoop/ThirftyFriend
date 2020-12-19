package com.example.ThriftyFriend.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ThriftyFriend.models.User;
import com.example.ThriftyFriend.repositories.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository uRepo;
	
	public User findById(Long id)
	{
		return this.uRepo.findById(id).orElse(null);
	}
	
	public User findByEmail(String email)
	{
		return this.uRepo.findByEmail(email);
	}
	
	public User registerUser(User u)
	{
		String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
		u.setPassword(hashed);
		return this.uRepo.save(u);
	}
	
	public boolean existsByEmail(String email)
	{
		return this.uRepo.existsByEmail(email);
	}
			
}
