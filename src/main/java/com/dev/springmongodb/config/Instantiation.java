package com.dev.springmongodb.config;

import com.dev.springmongodb.domain.Post;
import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.dto.AuthorDTO;
import com.dev.springmongodb.dto.CommentDTO;
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
        Post post2 = new Post(null,sdf.parse("25/02/2018"),"Janta de hoje","Hoje vamos jantar fora no novo restaurante",new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Bora jogar!!",sdf.parse("21/03/2018"),new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Quero tambem!",sdf.parse("21/03/2018"),new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Oba! Me chama tambem!",sdf.parse("25/02/2018"),new AuthorDTO(bob));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2)); //adiciona os posts na lista de posts da maria
        userRepository.save(maria); //salva maria
    } //
}
