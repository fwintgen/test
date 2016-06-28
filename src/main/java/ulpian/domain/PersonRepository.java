package ulpian.domain;

import org.springframework.data.domain.*;
import org.springframework.data.repository.CrudRepository;

import ulpian.domain.*;
public interface PersonRepository extends CrudRepository<Person, Long> {

    Page<Person> findAll(Pageable pageable);

    Person findByNameIgnoringCase(String name, String country);

}