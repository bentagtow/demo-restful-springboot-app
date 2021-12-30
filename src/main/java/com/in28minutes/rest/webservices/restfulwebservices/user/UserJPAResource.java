package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exception.InvalidUserException;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.post.Post;
import com.in28minutes.rest.webservices.restfulwebservices.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    //now we're making use of our useRepo in our JPA resource
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
//        return service.findAll();
        //Note: Now we're getting our users from the UserRepo rather than the
        // seeds in service
        return userRepository.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        //here we're returning an OPTIONAL of User.
        //if the user doesn't exist, it still comes back with a proper object
        //to avoid null pointer exception
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        EntityModel<User> resource = EntityModel.of(user.get());

        //this gives us a link with
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        //HATEOAS

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        //this is a validation method I added in to make sure that a name and bday are in there
        if (savedUser.getName() == null || savedUser.getBirthdate() == null) {
            throw new InvalidUserException("name or birthday missing");
        }

        //builds a specific uri for each user created at users/{id}
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).
                toUri();
        return ResponseEntity.created(location).build();


        //return a status of CREATED as per REST protocols

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int id) {

        //Note: Now we're getting our user POSTS from the UserRepo

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id- " + id);
        }

        return userOptional.get().getPosts();
    }

    //deletes a user obvi. Using ID uri
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);

    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@Valid @PathVariable int id, @RequestBody Post post) {

        Optional <User> userOptional = userRepository.findById(id);


        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id- " + id);
        }
        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        //builds a specific uri for each user created at users/{id}
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();


        //return a status of CREATED as per REST protocols

    }
//    @GetMapping("/users/{id}/posts")
//    public List retrievePosts(@PathVariable int id){
//        User user = service.findOne(id);
//        return user.getPosts();
//
//    }
//
//    @PostMapping("/users/{id}/posts")
//    public String createUserPost(@PathVariable int id){
//        String post = service.savePost(id, "my post!");
//        return post;
//    }
//

}
