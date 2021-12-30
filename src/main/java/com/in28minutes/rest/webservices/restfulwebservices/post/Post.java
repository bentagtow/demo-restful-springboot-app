package com.in28minutes.rest.webservices.restfulwebservices.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 1)
    private String description;

    //designates that a User has many to one relationship with post
    //fetch lazy prevents the recursion of user trying to fetch post and ...
    //post trying to fetch user
    @ManyToOne(fetch = FetchType.LAZY)
    //we also want to JSONIgnore because we don't want to get the details of the user
    @JsonIgnore
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
