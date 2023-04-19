package com.qa.springdemo.service;

import java.util.List;

import com.qa.springdemo.rest.domain.Cat;

public interface CatService {

    public Cat createCat(Cat cat);

    public List<Cat> getAll();

    public Cat get(int id);

    public Cat remove(int id);

    public Cat updateCat(int id, boolean hasWhiskers, boolean evil, String name, int length);
}
