package com.dev.springmongodb.services;

import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.dto.UserDTO;
import com.dev.springmongodb.repository.UserRepository;
import com.dev.springmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //pode ser injetavel em outras classes
public class UserService {

    @Autowired //instancia automaticamente
    private UserRepository repo;
    public List<User> findAll(){
        return repo.findAll();
    }
    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj){
        return repo.insert(obj);
    }

    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }
}
