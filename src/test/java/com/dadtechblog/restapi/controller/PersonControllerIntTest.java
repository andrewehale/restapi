package com.dadtechblog.restapi.controller;

import com.dadtechblog.restapi.RestApiApplication;
import com.dadtechblog.restapi.domain.Person;
import com.dadtechblog.restapi.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiApplication.class)
public class PersonControllerIntTest {

    private MockMvc restPersonMockMvc;
    private Person person;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PersonController personController = new PersonController(personRepository);
        restPersonMockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }
    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Person createEntity() {
        Person person = new Person().firstName("First").lastName("Last");
        return person;
    }

    @Before
    public void initTest() {
        person = createEntity();
    }


    @Test
    public void getPerson() throws Exception {

        // Initialize the database
        personRepository.saveAndFlush(person);


        restPersonMockMvc.perform(get("/api/persons/{id}", person.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(person.getId()))
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()));

        personRepository.deleteAll();
        personRepository.flush();
    }

}