package com.dadtechblog.restapi.controller;

import com.dadtechblog.restapi.domain.Person;
import com.dadtechblog.restapi.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/persons/{id}")
public class PersonController {
    private final PersonRepository personRepository;


    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Person getPerson(@PathVariable Long id) {
        Person person = personRepository.findOne(id);
        return person;
    }
}
