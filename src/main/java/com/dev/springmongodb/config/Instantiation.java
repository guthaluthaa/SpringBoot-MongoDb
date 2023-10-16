package com.dev.springmongodb.config;

import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration //configuracao
public class Instantiation implements CommandLineRunner {

    @Autowired //instancia auto e 'linka' pra outra classe
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

    } //
}
