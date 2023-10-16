package com.dev.springmongodb.repository;

import com.dev.springmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository //cria no mongoDB
public interface PostRepository extends MongoRepository<Post, String> { //vai gerenciar tipo User e o tipo de ID - crud imbutido no mongoRepo
}// acessa banco de dados
