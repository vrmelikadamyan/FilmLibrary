package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FilmDataBase {
    private ArrayList<Film> films;

    FilmDataBase() {
        films = new ArrayList<>();
        films.add(new Film("idfd1", Type.Film, "Титаник", Genre.Drama, 1997, 9.3, "some description"));
        films.add(new Film("idfd2", Type.Film, "Побег из Шоушенка", Genre.Drama, 1994, 5.8, "some description"));
        films.add(new Film("idff1", Type.Film, "Зеленая миля", Genre.Fantazy, 1999, 7.4, "some description"));
        films.add(new Film("idst1", Type.TVSeries, "Ходячие мертвецы", Genre.Thriller, 2010, 4.0, "some description"));
    }

    public boolean searchById() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите id фильма: ");
        String id = in.nextLine();
        System.out.println("Результаты поиска: ");
        int count = 0;
        for (int i = 0; i < films.size(); ++i) {
            if (films.get(i).isIdCorrect(id)) {
                ++count;
                printFilm(i, count);
            }
        }
        return count != 0;
    }

    public boolean searchByTitle(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название фильма: ");
        String title = in.nextLine();
        int count = 0;
        for (int i = 0; i < films.size(); ++i){
            if(films.get(i).isCoincidence(title)){
                ++count;
                printFilm(i, count);
            }
        }
        return count != 0;
    }

    public boolean searchByYear(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите год: ");
        int releaseYear = in.nextInt();
        int count = 0;
        for (int i = 0; i < films.size(); ++i) {
            if(films.get(i).isYearCorrect(releaseYear)){
                ++count;
                printFilm(i, count);
            }
        }
        return count != 0;
    }

    public void printFilm(int i, int count){
        films.get(i).printInfo(count);
    }

    public void showDetails(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите id нужного фильма: ");
        String id = in.nextLine();
        int count = 0;
        for (int i = 0; i < films.size(); ++i) {
            if (films.get(i).isIdCorrect(id)) {
                ++count;
                films.get(i).printFullInfo();
            }
        }
        if (count == 0) {
            System.out.println("Ничего не найдено.");
        }
    }

    public void addReview(User currentUser) {
        if(currentUser.isAdmin()){
            System.out.println("Администратор не может оставлять отзывы.");
        }
        else {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите id фильма: ");
            String id = in.nextLine();
            for (int i = 0; i < films.size(); ++i) {
                if (films.get(i).isIdCorrect(id)) {
                    System.out.println("Введите отзыв:");
                    String str = in.nextLine();
                    System.out.print("Введите рейтинг (от 0.0 до 10.0): ");
                    double rating = in.nextDouble();
                    Review review = new Review(str, currentUser.getNickName(), rating);
                    films.get(i).addReview(review);
                }
            }
        }
    }

    public void moderateReview(User currentUser) {
        if (currentUser.isAdmin()) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите id фильма: ");
            String id = in.nextLine();
            for (int i = 0; i < films.size(); ++i) {
                if (films.get(i).isIdCorrect(id)) {
                    films.get(i).moderateReview();
                }
            }
        }
        else {
            System.out.println("Отзывы может редактировать только администратор");
        }
    }
}
