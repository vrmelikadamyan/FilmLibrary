package com.company;

import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.printf("%d | id: %s | Название: %s | Год выпуска: %d | Рейтинг: %.1f\n", count, id, title, releaseYear, rating);
    }

    public void printFullInfo(){
        System.out.printf("id: %s | Название: %s | Тип: %s | Жанр: %s | Год выпуска: %d | Рейтинг: %.1f\n", id, title, type.toString(), genre.toString(), releaseYear, rating);
        System.out.printf("Описание:\n%s\n", description);
        System.out.println("_______________________________________________________");
        System.out.println("Отзывы:");
        printReviews();
    }

    public boolean isIdCorrect(String id){
        return this.id.equals(id);
    }

    public boolean isCoincidence(String title){
        return this.title.contains(title);
    }

    public boolean isYearCorrect(int releaseYear){
        return this.releaseYear == releaseYear;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void printReviews(){
        int number = 0;
        for(Review review : reviews){
            review.printReview(number);
            ++number;
        }
    }

    public void moderateReview(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер отзыва: ");
        int number = in.nextInt();
        while(number < 0 || number > (reviews.size()) - 1){
            System.out.print("Неверный номер. Попробуйте ещё раз: ");
            number = in.nextInt();
        }
        System.out.printf("Выберите действие:\n 1. Редактировать отзыв\n 2. Удалить отзыв\n 3. Назад\n");
        int d = in.nextInt();
        while(d < 1 || d > 3){
            System.out.print("Неверный пункт. Попробуйте ещё раз: ");
            d = in.nextInt();
        }

        switch (d){
            case 1: editReview(number);
            break;
            case 2: deleteReview(number);
            break;
            case 3:
                break;
        }
    }

    public void editReview(int number){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите отзыв: ");
        String text = in.nextLine();
        reviews.get(number).edit(text);
    }

    public void deleteReview(int number){
        reviews.remove(number);
    }
}
