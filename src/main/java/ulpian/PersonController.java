package ulpian;

import ulpian.domain.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value="/persons")
public class PersonController {

    Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository repository;

    @RequestMapping(value="/{person}", method= RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable Long person) {
        Person aPerson= repository.findOne(person);
        if (aPerson ==null ) {
        		return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(aPerson,HttpStatus.OK);
    }
    
    @RequestMapping(method= RequestMethod.GET)
    public Iterable<Person> getAllPersons() {
        Iterable<Person> persons= repository.findAll();
        return persons;
    }

    @RequestMapping( method= RequestMethod.PUT)
    public ResponseEntity<?> createPerson(@RequestBody Person person) {

        repository.save(person);
        
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    
}