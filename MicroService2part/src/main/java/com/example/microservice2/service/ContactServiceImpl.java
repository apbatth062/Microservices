package com.example.microservice2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.microservice2.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService {
	
	List<Contact>list=List.of(
			new Contact(11L,"abc@gmail.com","ap",1L),
			new Contact(12L,"adef@gmail.com","pa",2L),
			new Contact(13L,"qefc@gmail.com","sammer",1L),
			new Contact(14L,"yoo@gmail.com","yoo",3L)
			
			
			);
			

	@Override
	public List<Contact> getContacts(Long userId) {
		// TODO Auto-generated method stub
		return list.stream().filter(contact->contact.getUserId().equals(userId)).collect(Collectors.toList());
	}

}
