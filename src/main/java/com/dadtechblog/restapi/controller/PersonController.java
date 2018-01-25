package com.dadtechblog.restapi.controller;

import com.dadtechblog.restapi.domain.Person;
import com.dadtechblog.restapi.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonRepository personRepository;


    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        List<Person> persons = this.personRepository.findAll();
        return persons;
    }

    @GetMapping("/persons/{id}")
    public @ResponseBody Person getPerson(@PathVariable Long id) {
        Person person = personRepository.findOne(id);
        return person;
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) throws URISyntaxException {
        if (person.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Person result = personRepository.save(person);

        return ResponseEntity.created(new URI("/persons/" + result.getId()))
                .body(result);
    }

}
