package ulpian;

import ulpian.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class DatabaseLoader {

    private final PersonRepository repository;

    @Autowired
    public DatabaseLoader(PersonRepository repository){

        this.repository = repository;
    }

    @PostConstruct
    private void initDatabase(){

        Person peter = new Person("Peter");
        Person paul  = new Person("Paul");
        Person mary  = new Person("Mary");

        repository.save(peter);
        repository.save(paul);
        repository.save(mary);

    }
}