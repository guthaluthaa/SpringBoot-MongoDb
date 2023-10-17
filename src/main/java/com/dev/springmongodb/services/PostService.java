package com.dev.springmongodb.services;

import com.dev.springmongodb.domain.Post;
import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.dto.UserDTO;
import com.dev.springmongodb.repository.PostRepository;
import com.dev.springmongodb.repository.UserRepository;
import com.dev.springmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service //pode ser injetavel em outras classes
public class PostService {

    @Autowired //instancia automaticamente
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){ //metodo no servico para puxar do Repository
        return repo.findByTitleContaining(text);
    }

}
