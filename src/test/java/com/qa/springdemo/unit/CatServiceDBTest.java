package com.qa.springdemo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.springdemo.repo.CatRepo;
import com.qa.springdemo.rest.domain.Cat;
import com.qa.springdemo.service.CatService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatServiceDBTest {

    @Autowired
    private CatService service;

    @MockBean
    private CatRepo repo;


    @Test
    void testUpdate()
    {
        Long id = 1L;
        Mockito.when(this.repo.findById(id))
        .thenReturn(Optional.of(new Cat(false, false, null, 1, id)));
        
        Mockito.when(this.repo.save(new Cat(false, true, null, 1, id)))
        .thenReturn(new Cat(false, true, null, 1, id));


        assertEquals( new Cat(false, true, null, 1, id), service.updateCat(1, false, true, null, 1));

        Mockito.verify(repo, Mockito.times(1)).findById(id);
        Mockito.verify(repo, Mockito.times(1)).save(new Cat(false, true, null, 1, id));

    }

    @Test
    void testCreateCat()
    {
        Long id = 1L;
        
        Cat catCat = new Cat(false, true, null, 1, id);
        Mockito.when(this.repo.save(catCat))
        .thenReturn(catCat);


        assertEquals(catCat, service.createCat(catCat));

        Mockito.verify(repo, Mockito.times(1)).save(new Cat(false, true, null, 1, id));

    }

    @Test
    void testUpdateCat()
    {
        Long id = 1L;
        
        Cat catCat = new Cat(false, true, null, 1, id);
        List<Cat> catList = new ArrayList<Cat>();
        catList.add(catCat);
        Mockito.when(this.repo.findAll())
        .thenReturn(catList);


        assertEquals(catList, service.getAll());

        Mockito.verify(repo, Mockito.times(1)).findAll();

    }

    @Test
    void testGet()
    {
        Long id = 1L;
        int intId =1;
        
        Cat catCat = new Cat(false, true, null, 1, id);
        Mockito.when(this.repo.findById(id))
        .thenReturn(Optional.of(catCat));


        assertEquals(catCat, service.get(intId));

        Mockito.verify(repo, Mockito.times(1)).findById(id);

    }

    @Test
    void testRemove()
    {
        Long id = 1L;
        int intId =1;
        
        Cat catCat = new Cat(false, true, null, 1, id);
        Mockito.when(this.repo.findById(id))
        .thenReturn(Optional.of(catCat));


        assertEquals(catCat, service.remove(intId));

        Mockito.verify(repo, Mockito.times(1)).findById(id);

    }


}
