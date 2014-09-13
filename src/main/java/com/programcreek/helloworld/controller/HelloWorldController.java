package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.programcreek.helloworld.document.Users;
import com.programcreek.helloworld.repositories.UsersRepository;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	
	@Autowired
	private UsersRepository repository;

	String message = "Welcome to Spring MVC!";
	
	@RequestMapping(value = "/getusers", method = RequestMethod.GET)
	public ModelAndView helloWorld( ModelMap model ) {
		System.out.println("in hello ctrl");
		List<Users> users = repository.findAll();
 
		ModelAndView modelAndView = new ModelAndView("helloworld");		
		modelAndView.addObject("users", users );
		return modelAndView;
	}
	
	@RequestMapping("/getUsersList")
	public @ResponseBody List<Users> showUsers() {
				
		List<Users> users = repository.findAll();
		System.out.println("in the ctrl "+users.size());
		
		for(int i=0;i<users.size();i++){
			
			System.out.println(users.get(i).getFirstname());
		}
		return users;
	}
	@RequestMapping("/getUserByID")
	public @ResponseBody Users getUserByID(@RequestParam(value="id")String id) {
		
		Users user=new Users();
		try
		{
			user=repository.findOne(id);
			return user;
		}
		catch(Exception e)
		{
			System.out.println("exception simple name = "+e.getClass().getSimpleName());
			e.printStackTrace();
			
			if(e.getClass().getSimpleName().equalsIgnoreCase("DataAccessResourceFailureException"))
			{
				user.setMessage("Please Open MongoDB connection via command prompt.");
			}
		}
		return user;
	}

}
