package com.programcreek.helloworld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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
@RequestMapping("/home")
public class HelloWorldController {
	
	@Autowired
	private UsersRepository repository;
	
	@Autowired 
	private MongoOperations mongo;

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
	
	@RequestMapping("/saveUser")
	public @ResponseBody Users saveUser(@RequestParam(value="id")String id,
		@RequestParam(value="firstname")String firstname,@RequestParam(value="lastname")String lastname) {
		
		Users user=new Users();
		
		user.setId(getNextSequence("users")+"");
		user.setFirstname(firstname);
		user.setLastname(lastname);
		repository.save(user);
		
		return user;
	}
	
	private String getNextSequence(String collectionName) {
		
		//http://gerrydevstory.com/2013/05/16/auto-incrementing-field-on-spring-data-mongodb/
		System.out.println("in getNextSequence "+ collectionName);
		Query query = new Query(Criteria.where("_id").is(collectionName));
		System.out.println("1");
        Update update = new Update();
        update.inc("seqID", 1);
        
        System.out.println("2");
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        
        System.out.println("3");
        Users seqId = mongo.findAndModify(query, update, options, Users.class);
        System.out.println("seq id = ");
        return seqId.getId();
	  }
	
	/*public int getNextSequence1(String collectionName) {
		
		Users user = mongo.findAndModify(query(where("_id").is(collectionName)), new Update().inc("seq", 1),options().returnNew(true), Users.class);
		return user.getSeqID();
	}*/

}
