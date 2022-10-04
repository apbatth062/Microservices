package com.example.microservice2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice2.entity.Contact;
import com.example.microservice2.service.ContactService;

@RestController
@RequestMapping("/contact")
public class contactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/user/{userId}")
	public List<Contact> getContacts(@PathVariable("userId")Long useriD)
	{
		
	   return contactService.getContacts(useriD);	
	}

}
