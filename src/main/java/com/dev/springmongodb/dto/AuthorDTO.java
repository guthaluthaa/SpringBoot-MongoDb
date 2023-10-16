package com.dev.springmongodb.dto;

import com.dev.springmongodb.domain.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable { //projeta apenas os dados que quero do Author

    private String id;
    private String name;

    public AuthorDTO(){

    }

    public AuthorDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
