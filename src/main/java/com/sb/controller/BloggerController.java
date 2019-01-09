package com.sb.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sb.model.Blogger;
import com.sb.repo.BloggerRepository;


@RestController
@RequestMapping(value = "/blogger")
public class BloggerController {

	@Autowired
	BloggerRepository blogerRepository;
	
	@RequestMapping(value = "/")
    @ResponseBody
    public String index() {
		return "Welcome to Spring Boot - JPA - Postgres demo ---> running on IBM Cloud";
	}
	
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
		blogerRepository.save(new Blogger("Hemanth", "H G"));
		blogerRepository.save(new Blogger("IBM", "India"));
		blogerRepository.save(new Blogger("Logica", "India"));

		return "Done with saving bloggers";
	}
	
	@RequestMapping(value = "/save")
    @ResponseBody
    public String create(String firstName, String lastName) {
        try {
            Blogger blogger = new Blogger();
            blogger.setFirstName(firstName);
            blogger.setLastName(lastName);
            blogerRepository.save(blogger);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Blogger succesfully saved!";
    }
}

