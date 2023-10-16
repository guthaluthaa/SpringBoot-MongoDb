package com.dev.springmongodb.config;

import com.dev.springmongodb.domain.Post;
import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.dto.AuthorDTO;
import com.dev.springmongodb.repository.PostRepository;
import com.dev.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration //configuracao
public class Instantiation implements CommandLineRunner {

    @Autowired //instancia auto e 'linka' pra outra classe
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null,sdf.parse("21/03/2018"),"Codada hoje?","Vamos jogar cod hoje?!",new AuthorDTO(maria));
        Post post2 = new Post(null,sdf.parse("21/03/2018"),"Janta de hoje","Hoje vamos jantar fora no novo restaurante",new AuthorDTO(maria));

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2));

    } //
}
