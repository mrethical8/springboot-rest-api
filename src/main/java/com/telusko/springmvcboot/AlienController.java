package com.telusko.springmvcboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telusko.springmvcboot.model.Alien;

@Controller
public class AlienController {

	@Autowired
	AlienRepo repo;
	
	//@GetMapping("aliens") in produces attribute we commented it
	@GetMapping(path="aliens",produces={"application/xml"})//if i dont want client to request json then we use this we can specify from server side what you need
	@ResponseBody //response body is responsible to convert your object into json if u want to do reverse then we have to use requestbody
	public List<Alien> getAliens()
     {  
	   List<Alien> aliens = repo.findAll();
	   
	   return aliens; 
	   
	 }  
	
	@GetMapping ("alien/{aid}")
	@ResponseBody
	public Alien getAlien(@PathVariable("aid") int aid)
	{
		Alien alien = repo.findById(aid).orElse(new Alien(0,""));
		
		return alien;
	}
	
	//@PostMapping("alien") //commented in consumes attribute
	@PostMapping(path="alien",consumes= {"application/json"})
	//or
	//@PostMapping(path="alien",consumes= {MediaType.APPLICATION_JSON_VALUE})
	public Alien addAlien(@RequestBody Alien alien)//if u want to convert the data from the json to the java object you use requestbody
	{
		repo.save(alien);
		return alien;
	}
	
} 
