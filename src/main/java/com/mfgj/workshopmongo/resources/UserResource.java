package com.mfgj.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfgj.workshopmongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User mary = new User("1", "Mary Red", "mary@gmail.com");
		User john = new User("2", "John Black", "john@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(mary,john));
		return ResponseEntity.ok().body(list);
	}

}
