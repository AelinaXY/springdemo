package com.qa.springdemo.rest;

import com.qa.springdemo.rest.domain.Cat;
import com.qa.springdemo.service.CatService;
import com.qa.springdemo.service.CatServiceList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {

    CatService service = new CatServiceList();
    
    public CatController(CatService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String greeting() {
        return "Hello, World";
    }

    @PostMapping("/create")
    public ResponseEntity<Cat> createCat(@RequestBody Cat e) {
        return new ResponseEntity<>(service.createCat(e), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Cat> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Cat get(@PathVariable int id) {
        return service.get(id);
    }

    @DeleteMapping("/remove/{id}")
    public Cat remove(@PathVariable int id) {
        return service.remove(id);
    }

    @PatchMapping("/update/{id}")
    public Cat updateCat(
            @PathVariable int id,
            @RequestParam(name = "hasWhiskers", required = false) boolean hasWhiskers,
            @RequestParam(name = "evil", required = false) boolean evil,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "length", required = false) int length) {
        return service.updateCat(id, hasWhiskers, evil, name, length);
    }
}
