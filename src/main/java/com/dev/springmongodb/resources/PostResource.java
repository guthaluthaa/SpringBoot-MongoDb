package com.dev.springmongodb.resources;

import com.dev.springmongodb.domain.Post;
import com.dev.springmongodb.domain.User;
import com.dev.springmongodb.dto.UserDTO;
import com.dev.springmongodb.resources.util.URL;
import com.dev.springmongodb.services.PostService;
import com.dev.springmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
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

    @RequestMapping(value="/titlesearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text) {  //RequestParam - Pega parametro da url, nesse caso do text/ e se nao for informado retorna ""
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/fullsearch", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text",defaultValue = "") String text,
                                                 @RequestParam(value = "minDate",defaultValue = "") String minDate,
                                                 @RequestParam(value = "maxDate",defaultValue = "") String maxDate) {  //RequestParam - Pega parametro da url, nesse caso do text/ e se nao for informado retorna ""
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L)); //caso tenha erro usar o primeiro tempo criado (1970)
        Date max = URL.convertDate(maxDate, new Date()); //data com instante atual do sistema
        List<Post> list = service.fullSearch(text,min,max);
        return ResponseEntity.ok().body(list);
    }
}
