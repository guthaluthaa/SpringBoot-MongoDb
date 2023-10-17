package com.dev.springmongodb.resources;

import com.dev.springmongodb.domain.Post;
import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.dto.UserDTO;
import com.dev.springmongodb.services.PostService;
import com.dev.springmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//responsável por receber solicitações HTTP, processá-las e retornar respostas no formato desejado
@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}