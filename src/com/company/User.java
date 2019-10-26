package com.company;

public interface User {

    String getNickName();
    boolean isPasswordCorrect(String password);
    boolean isAdmin();
}
