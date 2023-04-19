package com.qa.springdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.springdemo.rest.domain.Cat;

public class CatServiceList implements CatService {

    List<Cat> catList = new ArrayList<Cat>();

    @Override
    public Cat createCat(Cat cat) {
        catList.add(cat);
        Cat catCreated = catList.get(catList.size() - 1);
        return catCreated;
    }

    @Override
    public List<Cat> getAll() {
        return catList;
    }

    @Override
    public Cat get(int id) {
        return catList.get(id);
    }

    @Override
    public Cat remove(int id) {
        return catList.remove(id);
    }

    @Override
    public Cat updateCat(int id, boolean hasWhiskers, boolean evil, String name, int length) {
        Cat updateCat = catList.get(id);
        updateCat.setHasWhiskers(hasWhiskers);
        updateCat.setEvil(evil);
        updateCat.setName(name);
        updateCat.setLength(length);
        return updateCat;
    }

}
