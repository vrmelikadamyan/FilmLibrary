package com.company;

import java.util.Scanner;

public class AppUser implements User {
    private String login;
    private String nickname;
    private String password;
    private boolean isAdmin;

    AppUser(String login, String nickname, String password, boolean isAdmin){
        this.login = login;
        this.nickname = nickname;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean isPasswordCorrect(String password){
        return this.password.equals(password);
    }

    public String getNickName(){
        return nickname;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    public void moderateReviews(){

    }
}
