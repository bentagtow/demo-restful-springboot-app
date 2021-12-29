package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exception.InvalidUserException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    //retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //retrieveUser using id as param
    //if no user, return UserNotFoundException using custom class with @ResponseStatus annotation that designates the status
    @GetMapping("users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> model = EntityModel.of(user);

        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    //post request for a new user:
    //  input: details of the user
    //  output: CREATED & return the created URI

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        if(savedUser.getName() == null || savedUser.getBirthdate()==null){
            throw new InvalidUserException("name or birthday missing");
        }



        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();
        return ResponseEntity.created(location).build();



        //return a status of CREATED as per REST protocols

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if (user == null){
            throw new UserNotFoundException("id"  + id);
        }
    }


    @GetMapping("/users/{id}/posts")
    public List retrievePosts(@PathVariable int id){
        User user = service.findOne(id);
        return user.getPosts();

    }

    @PostMapping("/users/{id}/posts")
    public String createUserPost(@PathVariable int id){
        String post = service.savePost(id, "my post!");
        return post;
    }


}