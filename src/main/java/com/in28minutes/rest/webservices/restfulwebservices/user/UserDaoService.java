package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        ArrayList<String> postsOne = new ArrayList<>();
        postsOne.add("user 1 posts!");
        ArrayList<String> postsTwo = new ArrayList<>();
        postsTwo.add("second user posts");
        ArrayList<String> postsThree = new ArrayList<>();
        postsThree.add("third user posts!");

        //adds seed users
        users.add(new User(1, "Adam", new Date(), postsOne));
        users.add(new User(2, "Eve", new Date(), postsTwo));
        users.add(new User(3, "Ben", new Date(), postsThree));
    }

    //find all users
    public List<User> findAll() {
        return users;
    }

    //saves new user by adding to users list. If no ID, assigns next int as ID.
    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }

        }
        return null;
    }

    //Iterator allows us to find the user by ID and delete them. returns the deleted user.
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
                if (user.getId() == id) {
                    iterator.remove();
                    return user;
                }

            }
        return null;


    }


    public String savePost(int id, String post){
        User user = findOne(id);
        List<String> posts = user.getPosts();
        posts.add(post);
        user.setPosts(posts);
        return null;
    }

}
