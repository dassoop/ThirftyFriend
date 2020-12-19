package com.example.ThriftyFriend.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.ThriftyFriend.models.User;
import com.example.ThriftyFriend.repositories.UserRepository;



@Component
public class UserValidator 
{
	@Autowired
	private UserRepository uRepo;
	
	public boolean supports(Class<?> clazz) 
 	{
        return User.class.equals(clazz);
    }
    
    public void validate(Object target, Errors errors) 
    {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) 
        {
            errors.rejectValue("password", "Match", "Passwords must match.");
        } 
        if (this.uRepo.existsByEmail(user.getEmail()))
        {
        	errors.rejectValue("email", "emailTaken", "That email already has an account.");
        }
    }
}
