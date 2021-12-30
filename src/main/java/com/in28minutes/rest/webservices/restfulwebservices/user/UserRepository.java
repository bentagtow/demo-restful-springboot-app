package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//needs repo annotation
//it's not a class it's an interface
//extends the JPA repo
//
@Repository
public interface UserRepository
        extends JpaRepository
        //the Entity being managed is User. Integer is primary key.
        <User, Integer> {


}
