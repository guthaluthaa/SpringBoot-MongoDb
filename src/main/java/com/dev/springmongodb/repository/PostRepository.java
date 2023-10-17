package com.dev.springmongodb.repository;

import com.dev.springmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //cria no mongoDB
public interface PostRepository extends MongoRepository<Post, String> { //vai gerenciar tipo User e o tipo de ID - crud imbutido no mongoRepo

    List<Post> findByTitleContaining(String text);  //query methods (findBy____Containing - use atributo da classe para pesquisa) - findByTitleContainingIgnoreCase() para ignorar maiusculas e minusculas
}// acessa banco de dados
