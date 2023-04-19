package com.qa.springdemo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.springdemo.exception.CatNotFoundException;
import com.qa.springdemo.repo.CatRepo;
import com.qa.springdemo.rest.domain.Cat;

@Primary
@Service
public class CatServiceDB implements CatService {

    private CatRepo repo;

    public CatServiceDB(CatRepo repo) {
        this.repo = repo;
    }

    @Override
    public Cat createCat(Cat cat) {
        return this.repo.save(cat);
    }

    @Override
    public List<Cat> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Cat get(int id) {
        return this.repo.findById(Long.valueOf(id)).orElseThrow(CatNotFoundException::new);
    }

    @Override
    public Cat remove(int id) {
        Cat returnCat = get(id);
        this.repo.deleteById(Long.valueOf(id));
        return returnCat;
    }

    @Override
    public Cat updateCat(int id, boolean hasWhiskers, boolean evil, String name, int length) {
        Cat updateCat = get(id);
        if (name != null) {
            updateCat.setName(name);
        }

        updateCat.setHasWhiskers(hasWhiskers);
        updateCat.setEvil(evil);
        updateCat.setLength(length);
        
        return this.repo.save(updateCat);

    }

}
