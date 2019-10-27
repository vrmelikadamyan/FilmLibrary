package com.company;

import java.util.Scanner;

public class Application {
    private UserDataBase userDB;
    private FilmDataBase filmDB;
    private User currentUser;

    Application() {
        userDB = new UserDataBase();
        filmDB = new FilmDataBase();
        userDB.addUser("admin", userDB.newUser("admin", "Admin", "admin", true));
        mainMenu();
    }

    public void mainMenu() {
        Scanner in = new Scanner(System.in);
        System.out.print("Меню:\n 1. Вход\n 2. Регистрация\nВыберите действие: ");
        int number = in.nextInt();
        while (!isNumberValid(number, 2)) {
            number = in.nextInt();
        }

        switch (number) {
            case 1:
                authorization();
                break;
            case 2:
                registration();
                break;
        }
    }

    public void authorization() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите логин: ");
        String login = input.nextLine();
        System.out.print("Введите пароль: ");
        String password = input.nextLine();
        while (!userDB.isUserValid(login, password)) {
            System.out.println("Неверный логин или пароль. Попробуйте снова.");
            System.out.print("Введите логин: ");
            login = input.nextLine();
            System.out.print("Введите пароль: ");
            password = input.nextLine();
        }
        currentUser = userDB.getUser(login);
        if (currentUser.isAdmin()) {
            adminMenu(currentUser);
        } else userMenu(currentUser);
    }

    public void registration() {
        Scanner input = new Scanner(System.in);
        System.out.print("Придумайте логин: ");
        String login = input.nextLine();
        while (userDB.isLoginRegistered(login)) {
            System.out.print("Логин занят! Придумайте другой: ");
            login = input.nextLine();
        }

        System.out.print("Придумайте nickname: ");
        String nickname = input.nextLine();

        System.out.print("Придумайте пароль: ");
        String password = input.nextLine();

        userDB.addUser(login, userDB.newUser(login, nickname, password, false));
        System.out.println("Вы зарегистрировались!");
        currentUser = userDB.getUser(login);
        userMenu(currentUser);
    }

    public void userMenu(User currentUser) {
        Scanner input = new Scanner(System.in);
        System.out.println("Добро пожаловать, " + currentUser.getNickName() + "!");
        System.out.print("Меню:\n 1. Поиск\n 2. Выйти\nВыберите действие: ");
        int number = input.nextInt();
        while (!isNumberValid(number, 2)) {
            number = input.nextInt();
        }

        switch (number) {
            case 1:
                search(currentUser);
                break;
            case 2:
                currentUser = null;
                mainMenu();
        }
    }

    public void adminMenu(User currentUser) {
        Scanner input = new Scanner(System.in);
        System.out.println("Добро пожаловать, " + currentUser.getNickName() + "!");
        System.out.print("Меню:\n 1. Поиск\n 2. Модерация отзывов\n 3. Выйти\nВыберите действие: ");
        int number = input.nextInt();
        while (!isNumberValid(number, 3)) {
            number = input.nextInt();
        }

        switch (number) {
            case 1:
                search(currentUser);
                break;
            case 2: //filmDB.moderateReview();
                break;
            case 3:
                currentUser = null;
                mainMenu();
        }
    }

    public void search(User currentUser) {
        Scanner input = new Scanner(System.in);
        System.out.print("Выберите тип поиска:\n 1. По индентификатору\n 2. По названию\n 3. По году выпуска\n 4. Назад\nВыберите действие: ");
        int number = input.nextInt();
        while (!isNumberValid(number, 4)) {
            number = input.nextInt();
        }
        boolean isSearchIsSuccessful = false;
        switch (number) {
            case 1:
                isSearchIsSuccessful = filmDB.searchById();
                break;
            case 2:
                isSearchIsSuccessful = filmDB.searchByTitle();
                break;
            case 3:
                isSearchIsSuccessful = filmDB.searchByYear();
                break;
            case 4:
                if (currentUser.isAdmin()) {
                    adminMenu(currentUser);
                } else userMenu(currentUser);
                break;
        }

        if (isSearchIsSuccessful) {
            System.out.printf(" 1. Посмотреть детали фильма\n 2. Оставить отзыв\n 3. Редактировать отзывы\n 4. Назад\nВыберите действие: ");
            number = input.nextInt();
            while (!isNumberValid(number, 4)) {
                number = input.nextInt();
            }

            switch (number) {
                case 1:
                    filmDB.showDetails();
                    break;
                case 2:
                    filmDB.addReview(currentUser);
                    break;
                case 3:
                    filmDB.moderateReview(currentUser);
                    break;
                case 4:
                    search(currentUser);
                    break;
            }
        } else {
            System.out.printf("Ничего не найдено.\n");
        }
        System.out.printf("1. Назад\n");
        number = input.nextInt();
        while (!isNumberValid(number, 1)) {
            number = input.nextInt();
        }
        search(currentUser);
    }

    public boolean isNumberValid(int number, int k){
        if (number < 1 || number > k){
            System.out.println("Вы ввели неверный пункт, попробуйте ещё раз: ");
            return false;
        }
        return true;
    }
}
