package com.mfgj.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mfgj.workshopmongo.config.dto.AuthorDTO;
import com.mfgj.workshopmongo.config.dto.CommentDTO;
import com.mfgj.workshopmongo.domain.Post;
import com.mfgj.workshopmongo.domain.User;
import com.mfgj.workshopmongo.repository.PostRepository;
import com.mfgj.workshopmongo.repository.UserRepository;

@Configuration
public class Instatatiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User mary = new User(null, "Mary Brown", "mary@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(mary, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("26/06/2022"), "I'm going to travel.", "I'm going to travel to Canada.", new AuthorDTO(mary));
		Post post2 = new Post(null, sdf.parse("26/06/2022"), "Good moring!.", "I'm very happy today!", new AuthorDTO(mary));
		
		CommentDTO c1  = new CommentDTO("Good traval my friend!", sdf.parse("26/06/2022"), new AuthorDTO(alex));
		CommentDTO c2  = new CommentDTO("Enjoy the trip!", sdf.parse("26/06/2022"), new AuthorDTO(bob));
		CommentDTO c3  = new CommentDTO("Have a great day!", sdf.parse("26/06/2022"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		mary.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.saveAll(Arrays.asList(mary));		
		
	}

}
