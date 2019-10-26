package com.company;

import java.util.ArrayList;

public class Film {
    private String id;
    private Type type;
    private String title;
    private Genre genre;
    private int releaseYear;
    private double rating;
    private String description;
    private ArrayList<Review> reviews;

    Film(String id, Type type, String title, Genre genre, int releaseYear, double rating, String description) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.description = description;
        reviews = new ArrayList<Review>();
    }

    public String getId(){
        return id;
    }

    public void printInfo(int count){
        System.out.printf("%d | id: %s | Название: %s | Жанр: %s | Год выпуска: %d\n", count, id, title, genre.toString(), releaseYear);
    }

    public void printFullInfo(){
        System.out.printf("id: %s | Название: %s | Тип: %s | Жанр: %s | Год выпуска: %d | Рейтинг: %f\n", id, title, type.toString(), genre.toString(), releaseYear, rating);
        System.out.printf("Описание:\n%s\n", description);
    }
}
