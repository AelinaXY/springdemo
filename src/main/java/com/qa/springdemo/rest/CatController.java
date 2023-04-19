package com.qa.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springdemo.rest.domain.Cat;
@RestController
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

    @GetMapping("/getAll")
    public List<Cat> getAll()
    {
        return catList;
    }

    @GetMapping("/get/{id}")
    public Cat get(@PathVariable int id)
    {
        return catList.get(id);
    }

    @DeleteMapping("/remove/{id}")
    public Cat remove(@PathVariable int id)
    {
        return catList.remove(id);
    }
}
