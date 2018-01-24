package com.dadtechblog.restapi.repository;

import com.dadtechblog.restapi.RestApiApplication;
import com.dadtechblog.restapi.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiApplication.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findByLastName() throws Exception {
        Person person1 = new Person().firstName("First1").lastName("Last");
        personRepository.save(person1);


        Person person2 = new Person().firstName("First2").lastName("Last2");
        personRepository.save(person2);


        Person person3 = new Person().firstName("First3").lastName("Last");
        personRepository.save(person3);

        personRepository.flush();

        List<Person> results = personRepository.findByLastName("Last");

        assertThat(results.size()).isEqualTo(2);

        assertThat(results).contains(person1).contains(person3).doesNotContain(person2);

        personRepository.deleteAll();
        personRepository.flush();
    }

}