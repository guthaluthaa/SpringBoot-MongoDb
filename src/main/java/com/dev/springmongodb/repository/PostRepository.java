package com.dev.springmongodb.repository;

import com.dev.springmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository //cria no mongoDB
public interface PostRepository extends MongoRepository<Post, String> { //vai gerenciar tipo User e o tipo de ID - crud imbutido no mongoRepo

    @Query("{'title':  {$regex: ?0, $options: 'i'}}") //?0 é o primeiro parametro
    List<Post> searchTitle(String text);

    List<Post> findByTitleContaining(String text);  //query methods (findBy____Containing - use atributo da classe para pesquisa) - findByTitleContainingIgnoreCase() para ignorar maiusculas e minusculas ---- busca com spring -> https://docs.spring.io/spring-data/data-document/docs/current/reference/html/#mongodb.repositories.queries

    @Query("{$and: [{ date: { $gte: ?1 } },{ date: { $lte: ?2 } },{ $or: [ {'title':  {$regex: ?0, $options: 'i'}}, {'body':  {$regex: ?0, $options: 'i'}}, {'comments.text':  {$regex: ?0, $options: 'i'}} ] }]}")  // parametros especificos - https://www.mongodb.com/docs/manual/reference/operator/query/gte/
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
