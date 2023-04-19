package com.qa.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.springdemo.rest.domain.Cat;

public class CatController {

    List<Cat> catList = new ArrayList<Cat>();

    @GetMapping("/")
    public String greeting() {
        return "Hello, World";
    }

    @PostMapping("/create")
    public Cat createCat(@RequestBody Cat e) {
        this.catList.add(e);
        return this.catList.get(catList.size() - 1);
    }
}
