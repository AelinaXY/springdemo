package com.qa.springdemo.rest;

import com.qa.springdemo.rest.domain.Cat;
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

    List<Cat> catList = new ArrayList<Cat>();

    @GetMapping("/")
    public String greeting() {
        return "Hello, World";
    }

    @PostMapping("/create")
    public ResponseEntity<Cat> createCat(@RequestBody Cat e) {
        catList.add(e);
        Cat catCreated = catList.get(catList.size() - 1);
        return new ResponseEntity<>(catCreated, HttpStatus.CREATED);
        // return this.catList.get(catList.size() - 1);
    }

    @GetMapping("/getAll")
    public List<Cat> getAll() {
        return catList;
    }

    @GetMapping("/get/{id}")
    public Cat get(@PathVariable int id) {
        return catList.get(id);
    }

    @DeleteMapping("/remove/{id}")
    public Cat remove(@PathVariable int id) {
        return catList.remove(id);
    }

    @PatchMapping("/update/{id}")
    public Cat updateCat(
            @PathVariable int id,
            @RequestParam(name = "hasWhiskers", required = false) boolean hasWhiskers,
            @RequestParam(name = "evil", required = false) boolean evil,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "length", required = false) int length) {
        Cat updateCat = catList.get(id);
        updateCat.setHasWhiskers(hasWhiskers);
        updateCat.setEvil(evil);
        updateCat.setName(name);
        updateCat.setLength(length);
        return updateCat;
    }
}
