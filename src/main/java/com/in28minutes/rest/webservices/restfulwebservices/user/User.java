package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.post.Post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

//this annotation makes this an entity managed by JPA
@Entity
public class User {

    //@Id makes this the primary key
    //@GeneratedValue provides the specification for generation strategies of the primary key
    @Id
    @GeneratedValue
    private Integer id;

    //size is validation
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    //past is validation (past date)
    @Past
    private Date birthdate;

    //user has one-to-many relationship with post (post has many-to-one wiht user)
    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public User(Integer id, String name, Date birthdate) {
        super();
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
//        this.posts = posts;

    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

//    public List<String> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<String> posts) {
//        this.posts = posts;
//    }


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }


}
