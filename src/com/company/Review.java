package com.company;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Review {
    private int number;
    private String text;
    private String author;
    private LocalDate date;
    private LocalTime time;
    private double rating;

    public Review(String text, String author, double rating){
        number = 0;
        this.text = text;
        this.author = author;
        date = LocalDate.now();
        time = LocalTime.now();
        this.rating = rating;
    }

    public void printReview(int number){
        this.number = number;
        System.out.printf("№ %d | Автор: %s | Дата: %s %s\n%s\nРейтинг: %.1f\n", number, author, date, time, text, rating);
        System.out.println("_______________________________________________________");
    }

    public void edit(String text) {
        this.text = text + " (Edited by Admin)";
    }
}
