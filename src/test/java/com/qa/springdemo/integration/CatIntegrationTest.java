package com.qa.springdemo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springdemo.rest.domain.Cat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CatIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception
    {
        Cat newCat = new Cat(true, "Evil Cat", true, 19);
        String newCatAsJson = this.mapper.writeValueAsString(newCat);
        RequestBuilder req = post("/create").content(newCatAsJson).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkResultMatcher = status().isCreated();
        Cat resultCat = new Cat(true, true, "Evil Cat", 19, 1L);
        String resultCatAsJson = mapper.writeValueAsString(resultCat);
        ResultMatcher checkBody = content().json(resultCatAsJson);
        this.mvc.perform(req).andExpect(checkResultMatcher).andExpect(checkBody);

    }
}
