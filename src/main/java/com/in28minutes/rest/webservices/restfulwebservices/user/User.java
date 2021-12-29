package com.in28minutes.rest.webservices.restfulwebservices.user;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    //positive is a validation
    @Positive
    private Integer id;

    //size is validation
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    //past is validation (past date)
    @Past
    private Date birthdate;

    private List<String> posts;

    public User(Integer id, String name, Date birthdate, ArrayList<String> posts) {
        super();
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.posts = posts;

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

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
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
