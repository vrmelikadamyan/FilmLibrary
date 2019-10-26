package com.company;
import java.util.HashMap;

public class UserDataBase {
    private HashMap<String, User> db;

    UserDataBase() {
        db = new HashMap<>();
    }

    User newUser(String login, String nickname, String password, boolean isAdmin){
        User user = new AppUser(login, nickname, password, isAdmin);
        return user;
    }

    public void addUser(String login, User user){
        db.put(login, user);
    }

    boolean isLoginRegistered(String login){
        return db.containsKey(login);
    }

    boolean isUserValid(String login, String password){
        if(db.containsKey(login)){
            if(db.get(login).isPasswordCorrect(password)){
                return true;
            }
        }
        return false;
    }

    public User getUser(String login){
        return db.get(login);
    }
}
