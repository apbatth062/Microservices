package com.example.microservice2.service;

import java.util.List;

import com.example.microservice2.entity.Contact;

public interface ContactService {
	
	public List<Contact>getContacts(Long userId);

}
