package com.dev.springmongodb.resources;

import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.dto.UserDTO;
import com.dev.springmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//responsável por receber solicitações HTTP, processá-las e retornar respostas no formato desejado
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)  //OU @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){ //ResponseEntity retorna em um melhor formato com cabeçalhos personalizados etc
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); //colecao compativel com lambda (stream), transforma o x em DTO e retorna volta pra lista com collec
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)  //OU @GetMapping
    public ResponseEntity<UserDTO> findById(@PathVariable String id){ //ResponseEntity retorna em um melhor formato com cabeçalhos personalizados etc //PathVari pega do {id}
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)  //OU @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //pega o endereco do novo elemento criado
        return ResponseEntity.created(uri).build();  //.created responde 201 (novo criado) e a localização do novo elem. criado
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)  //OU @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
