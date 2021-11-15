package com.example.ThriftyFriend.controllers;

import java.util.List;

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

import com.example.ThriftyFriend.models.ListingSummary;
import com.example.ThriftyFriend.models.SummaryHistoryLog;
import com.example.ThriftyFriend.models.User;
import com.example.ThriftyFriend.services.APIAuthService;
import com.example.ThriftyFriend.services.APISearchService;
import com.example.ThriftyFriend.services.ListingSummaryService;
import com.example.ThriftyFriend.services.SummaryHistoryLogService;
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
	@Autowired
	private APISearchService searchService;
	@Autowired
	private APIAuthService authService;
	@Autowired
	private SummaryHistoryLogService logService;
	
//HOMEPAGE - View main search home page
	@GetMapping("/")
	public String viewHomepage(HttpSession session, Model m)
	{
		if(session.getAttribute("user_id")!=null)
		{
			m.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		}
		session.setAttribute("token", this.authService.getAuthToken());
		return "index.jsp";
	}
	
//VIEW LOGIN/REG - View login and registration page
	@GetMapping("/loginReg")
	public String viewLoginReg(@ModelAttribute("user")User u, Model m, HttpSession session)
	{
		if(session.getAttribute("user_id")!=null)
		{
			m.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		}
		return "loginReg.jsp";
	}
	
//REGISTER USER POST - Mapping to post register User form 	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user")User u, BindingResult result, Model m, HttpSession session)
	{
		uValid.validate(u, result);
		if(result.hasErrors())
		{
			return "original/loginReg.jsp";
		}
		this.uService.registerUser(u);
		session.setAttribute("user_id", u.getId());
		return "redirect:/dashboard";
	}
	
//VIEW DASHBOARD - View user Dashboard page 
	@GetMapping("/dashboard")
	public String viewSearchListings(HttpSession session, RedirectAttributes redirect, Model m)
	{
		if(session.getAttribute("user_id")==null)
		{
			redirect.addFlashAttribute("notLoggedError", "You must be logged in to view that page");
			return "redirect:/loginReg";
		}
		m.addAttribute("user", this.uService.findById((Long)session.getAttribute("user_id")));
		//get user from session
		Long userID = (Long)session.getAttribute("user_id");
		User user = this.uService.findById(userID);
		//get the list of watched items
		List<ListingSummary> listings = user.getListings();
		//display the listings
		m.addAttribute("watched_items", listings);
		
		
		return "original/dashboard.jsp";
	}
//LOGIN USER POST - Mapping to post login User form 
	@PostMapping("/login")
	public String login(@RequestParam("email")String email, @RequestParam("password")String password, HttpSession session, RedirectAttributes redirect)
	{
		//Check if user is already logged in 
		if(session.getAttribute("user_id")!=null)
		{
			redirect.addFlashAttribute("alreadyLoggedError", "You are already logged in as " + this.uService.findById((Long)session.getAttribute("user_id")).getName());
			return "redirect:/loginReg";
		}
		//Check if either login form fields are blank
		if(email == "" | password == "")
		{
			redirect.addFlashAttribute("loginBlankError", "Your login info cannot be blank.");
			return "redirect:/loginReg";
		}
		//Check if the user email is registered in database
		if(!this.uService.existsByEmail(email))
		{
			redirect.addFlashAttribute("userNotFoundError", "That user does not exist.");
			return "redirect:/loginReg";
		}
		//Find user and check password hash
		User u = this.uService.findByEmail(email);
		if(!BCrypt.checkpw(password, u.getPassword()))
		{
			redirect.addFlashAttribute("loginError", "Login information not correct.");
			return "redirect:/loginReg";
		}
		session.setAttribute("user_id", u.getId());
		return "redirect:/dashboard";
	}
	
//LOGOUT GET MAPPING - Post to log out user by invalidiating the httpsession
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/loginReg";
	}
	
//CREATE SUMMARY - Path to create summary with information pulled from "ListingItem" objects math displayed on the page. 
	@GetMapping("/summary/create/{name}/{avg}/{min}/{max}")
	public String createSummary(@PathVariable("name")String name, @PathVariable("avg")double avg, @PathVariable("min")double min, @PathVariable("max")double max, RedirectAttributes redirect)
	{
		name = name.trim();
		if(this.sumService.existsByName(name))
		{
			redirect.addFlashAttribute("summaryExistsError", "There is already a listing summary by that name. Please use the search bar to find it.");
			return "redirect:/searchRequest/{name}";
		}
		else
		{
			this.sumService.createListingSummary(name, avg, min, max);
			return "redirect:/searchRequest/{name}";
		}
	}
	
//VIEW SUMMARY - Path to view a specific ListingSummary by ID 
	@GetMapping("/summary/{id}/view")
	public String viewSummary(@PathVariable("id")Long id, Model m, HttpSession session)
	{	
		//Check if there is a User logged in to display their name
		if(session.getAttribute("user_id") != null)
		{
			User u = this.uService.findById((Long)session.getAttribute("user_id"));
			m.addAttribute("user", u);
		}
		
		ListingSummary sum = this.sumService.findById(id);
		List<SummaryHistoryLog> historyLogs = this.logService.findAllBySummary(sum);
		
		m.addAttribute("historyLogs", historyLogs);
		m.addAttribute("summary", sum);
		return "original/viewSummary.jsp";
	}
	
//Adding a summary to a user watch list
	@GetMapping("/summary/add/{id}")
	public String addListing(@PathVariable("id")Long id, Model m, HttpSession session, RedirectAttributes redirect) 
	{
		if(session.getAttribute("user_id")==null)
		{
			redirect.addFlashAttribute("watchlistError", "You must be logged in to do that.");
			return "redirect:/summary/{id}/view";
		}
		Long userID = (Long)session.getAttribute("user_id");
		//get the summary id
		Long summaryId = id;
		User user = this.uService.findById(userID);
		ListingSummary selectedListing = this.sumService.findById(summaryId);
		this.uService.addSummaryToWatchList(user, selectedListing);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/summary/remove/{id}")
	public String removeListing(@PathVariable("id")Long id, Model m, HttpSession session) 
	{
		//get user from session
		Long userID = (Long)session.getAttribute("user_id");
		//get the summary id
		Long summaryId = id;
		User user = this.uService.findById(userID);
		ListingSummary selectedListing = this.sumService.findById(summaryId);
		this.uService.removeSummaryFromWatchList(user, selectedListing);
		return "redirect:/dashboard";
	}
}
