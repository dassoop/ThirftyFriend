package com.example.ThriftyFriend.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ThriftyFriend.models.User;
import com.example.ThriftyFriend.services.ListingSummaryService;
import com.example.ThriftyFriend.services.UserService;
import com.example.ThriftyFriend.validators.UserValidator;

@Controller
public class MainController 
{
	@Autowired 
	private UserValidator uValid;
	@Autowired
	private UserService uService;
	@Autowired
	private ListingSummaryService sumService;
	
	@GetMapping("/")
	public String viewHomepage(HttpSession session, Model m)
	{
		if(session.getAttribute("user_id")!=null)
		{
			m.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		}
		return "homepage.jsp";
	}
	
	@GetMapping("/loginReg")
	public String viewLoginReg(@ModelAttribute("user")User u, Model m, HttpSession session)
	{
		if(session.getAttribute("user_id")!=null)
		{
			m.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		}
		return "loginReg.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user")User u, BindingResult result, Model m, HttpSession session)
	{
		uValid.validate(u, result);
		if(result.hasErrors())
		{
			return "loginReg.jsp";
		}
		this.uService.registerUser(u);
		session.setAttribute("user_id", u.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String viewSearchListings(HttpSession session, RedirectAttributes redirect, Model m)
	{
		if(session.getAttribute("user_id")==null)
		{
			redirect.addFlashAttribute("notLoggedError", "You must be logged in to view that page");
			return "redirect:/loginReg";
		}
		m.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		return "dashboard.jsp";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email, @RequestParam("password")String password, HttpSession session, RedirectAttributes redirect)
	{
		if(session.getAttribute("user_id")!=null)
		{
			redirect.addFlashAttribute("alreadyLoggedError", "You are already logged in as " + this.uService.findById((Long)session.getAttribute("user_id")).getName());
			return "redirect:/loginReg";
		}
		User u = this.uService.findByEmail(email);
		if(!BCrypt.checkpw(password, u.getPassword()))
		{
			redirect.addFlashAttribute("loginError", "Login information not correct or email not found.");
			return "redirect:/loginReg";
		}
		session.setAttribute("user_id", u.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/loginReg";
	}
	
	@GetMapping("/summary/create/{name}/{avg}/{min}/{max}")
	public String createSummary(@PathVariable("name")String name, @PathVariable("avg")double avg, @PathVariable("avg")double min, @PathVariable("avg")double max, RedirectAttributes redirect)
	{
		if(this.sumService.existsByName(name))
		{
			redirect.addFlashAttribute("summaryExistsError", "There is already a listing summary by that name. Please use the search bar to find it.");
			return "redirect:/viewListings/{name}";
		}
		else
		{
			this.sumService.createListingSummary(name, avg, min, max);
			return "redirect:/viewListings/{name}";
		}
	}
}
