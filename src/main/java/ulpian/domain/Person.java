package ulpian.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;

@Getter
@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false)
    private String name;

    //private EMail email;

    //private Telephone telephone;

    protected Person(){
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public Person(String name){
        this.name = name;
    }
}