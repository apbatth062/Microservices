package com.example.microservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.microservice.entity.User;
@Service
public class userServiceImpl  implements userService{

	//fake user list
	List<User> list=List.of(new User(1L,"amrit","1234567"),
			new User(2L,"andy","1237"),
			new User(3L,"guru","12799"));
	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return list.stream().filter(user->user.getUserId().equals(id)).findAny().orElse(null) ;
	}

}
