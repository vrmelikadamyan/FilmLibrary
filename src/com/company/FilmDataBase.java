package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmDataBase {
    private ArrayList<Film> films;

    FilmDataBase() {
        films = new ArrayList<>();
        films.add(newFilm("idfd1", Type.Фильм, "Titanic", Genre.Драма, 1997, 9.3, "some description"));
        films.add(newFilm("idfd2", Type.Фильм, "The Shawshank Redemption", Genre.Драма, 1994, 5.8, "some description"));
        films.add(newFilm("idff1", Type.Фильм, "The Green Mile", Genre.Фэнтези, 1999, 7.4, "some description"));
        films.add(newFilm("idst1", Type.Сериал, "The Walking Dead", Genre.Триллер, 2010, 4.0, "some description"));
    }

    public Film newFilm(String id, Type type, String title, Genre genre, int releaseYear, double rating, String description) {
        Film film = new Film(id, type, title, genre, releaseYear, rating, description);
        return film;
    }

    public void searchById() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите id фильма: ");
        String id = in.nextLine();
        System.out.println("Результаты поиска: ");
        int count = 0;
        for (int i = 0; i < films.size(); ++i) {
            if (films.get(i).getId().equals(id)) {
                ++count;
                printFilm(i, count);
            }
        }
        if (count == 0) {
            System.out.println("Ничего не найдено.");
        }
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
            if (films.get(i).getId().equals(id)) {
                ++count;
                films.get(i).printFullInfo();
            }
        }
        if (count == 0) {
            System.out.println("Ничего не найдено.");
        }
    }
}
