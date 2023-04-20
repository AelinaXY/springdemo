package com.qa.springdemo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springdemo.rest.domain.Cat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:cat-schema.sql", "classpath:cat-data.sql" })
public class CatIntegrationTest{

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        Cat newCat = new Cat(true, "Evil Cat", true, 19);
        String newCatAsJson = this.mapper.writeValueAsString(newCat);
        RequestBuilder req = post("/create").content(newCatAsJson).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkResultMatcher = status().isCreated();
        Cat resultCat = new Cat(true, true, "Evil Cat", 19, 2L);
        String resultCatAsJson = mapper.writeValueAsString(resultCat);
        ResultMatcher checkBody = content().json(resultCatAsJson);
        this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
    }

    @Test
    void testCreateOneLine() throws Exception {
        this.mvc.perform(post("/create").content(mapper.writeValueAsString(new Cat(true, "Evil Cat", true, 19)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(new Cat(true, true, "Evil Cat", 19, 2L))));
    }

    @Test
    void testGet() throws Exception {
        int catId = 1;
        RequestBuilder req = get("/get/" + catId);

        ResultMatcher checkResultMatcher = status().isOk();
        Cat resultCat = new Cat(true, true, "name", 16, 1L);
        String resultCatAsJson = mapper.writeValueAsString(resultCat);
        ResultMatcher checkBody = content().json(resultCatAsJson);
        this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
    }
    @Test
    void testGetAll() throws Exception {
        RequestBuilder req = get("/getAll");

        ResultMatcher checkResultMatcher = status().isOk();
        List<Cat> catList = new ArrayList<Cat>();
        catList.add(new Cat(true, true, "name", 16, 1L));
        String resultCatListAsJson = mapper.writeValueAsString(catList);
        ResultMatcher checkBody = content().json(resultCatListAsJson);
        this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
    }

    @Test
    void testDelete() throws Exception {
        int catId = 1;
        RequestBuilder req = delete("/remove/" + catId);

        ResultMatcher checkResultMatcher = status().isOk();
        Cat resultCat = new Cat(true, true, "name", 16, 1L);
        String resultCatAsJson = mapper.writeValueAsString(resultCat);
        ResultMatcher checkBody = content().json(resultCatAsJson);
        this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
    }

    @Test
    void testUpdate() throws Exception {
        int catId = 1;
        RequestBuilder req = patch("/update/" + catId)
        .param("evil", "false")
        .param("hasWhiskers", "false")
        .param("name","testName")
        .param("length","67");

        ResultMatcher checkResultMatcher = status().isOk();
        Cat resultCat = new Cat(false, false, "testName", 67, 1L);
        String resultCatAsJson = mapper.writeValueAsString(resultCat);
        ResultMatcher checkBody = content().json(resultCatAsJson);
        this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
    }

    // @Test
    // void testUpdateMap() throws Exception {
    //     int catId = 1;
    //     RequestBuilder req = patch("/update/" + catId).params("evil", "false").param("hasWhiskers", "false").param("name","testName").param("length","67");

    //     ResultMatcher checkResultMatcher = status().isOk();
    //     Cat resultCat = new Cat(false, false, "testName", 67, 1L);
    //     String resultCatAsJson = mapper.writeValueAsString(resultCat);
    //     ResultMatcher checkBody = content().json(resultCatAsJson);
    //     this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);
    // }

    @Test
    void testNotFound() throws Exception {
        int catId = 2;
        RequestBuilder req = get("/get/" + catId);

        ResultMatcher checkResultMatcher = status().isNotFound();
        this.mvc.perform(req).andExpect(checkResultMatcher);
    }
    
}
