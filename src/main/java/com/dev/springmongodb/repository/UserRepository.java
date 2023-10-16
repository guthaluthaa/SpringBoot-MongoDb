package com.dev.springmongodb.repository;

import com.dev.springmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface UserRepository extends MongoRepository<User, String> { //vai gerenciar tipo User e o tipo de ID - crud imbutido no mongoRepo
}// acessa banco de dados
