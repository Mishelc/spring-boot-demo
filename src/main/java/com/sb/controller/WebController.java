package com.sb.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb.model.Blogger;
import com.sb.repo.BloggerRepository;


@RestController
public class WebController {

	@Autowired
	BloggerRepository blogerRepository;
	
	
	@RequestMapping("/findall")
	public String findAll(){
		String result = "";
		
		for(Blogger blogger : blogerRepository.findAll()){
			result += blogger.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("bloggerid") long bloggerid){
		String result = "";
		result = blogerRepository.findById(bloggerid).toString();
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "";
		
		for(Blogger blogger: blogerRepository.findByLastName(lastName)){
			result += blogger.toString() + "<br>"; 
		}
		
		return result;
	}
	
	@RequestMapping("/savebloggers")
	public String savebloggers(){
		// save blogger details
		blogerRepository.save(new Blogger("Rajesh", "Gudikoti"));
		

		return "Done";
	}
}

