package emanuele_mangano;

import com.github.javafaker.Faker;
import emanuele_mangano.Entities.Book;
import emanuele_mangano.Entities.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        List<Book> booksList = new ArrayList<>();
        List<Magazine> magazinesList = new ArrayList<>();
        Faker faker = new Faker();
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        int choice, choiceLength;

        Supplier<Book> bookSupplier = () -> {
            String ISBN = String.valueOf(rnd.nextInt(900000000, 999999999));
            return new Book(faker.book().author(), faker.book().genre(), ISBN, faker.book().title(), rnd.nextInt(1800, 2024), rnd.nextInt(30, 400));
        };

        Supplier<Magazine> magazineSupplier = () -> {
            String ISBN = String.valueOf(rnd.nextInt(900000000, 999999999));
            String[] recurrence = {"Monthly", "Weekly", "Semiannual"};
            return new Magazine(ISBN, faker.book().title(), rnd.nextInt(1960, 2024), rnd.nextInt(30, 400), recurrence[rnd.nextInt(0, 2)]);
        };


        while (true) {
            System.out.println("******** MENU ********" + System.lineSeparator() + "1. Generate one or more Books" + System.lineSeparator() + "2. Generate one or more Magazines"
                    + System.lineSeparator() + "3. Print All the books and Magazines " + System.lineSeparator() + "0. Exit");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("How much books you wanna generate? ");
                    choiceLength = Integer.parseInt(sc.nextLine());
                    addBook(booksList, bookSupplier, choiceLength);
                    System.out.println(choiceLength + " books successfully created");
                    break;

                case 2:
                    System.out.println("How much books you wanna generate? ");
                    choiceLength = Integer.parseInt(sc.nextLine());
                    addMagazine(magazinesList, magazineSupplier, choiceLength);
                    System.out.println(choiceLength + " magazines successfully created");
                    break;

                case 3:
                    System.out.println(System.lineSeparator() + "BOOKS");
                    for (Book book : booksList) {
                        System.out.println(book);
                    }
                    System.out.println(System.lineSeparator() + "MAGAZINES");
                    for (Magazine magazine : magazinesList) {
                        System.out.println(magazine);
                    }
            }
        }

    }

    public static void addBook(List<Book> books, Supplier<Book> bookSupplier, int length) {
        for (int i = 0; i < length; i++) {
            books.add(bookSupplier.get());
        }
    }

    public static void addMagazine(List<Magazine> magazines, Supplier<Magazine> magazineSupplier, int lenght) {
        for (int i = 0; i < lenght; i++) {
            magazines.add(magazineSupplier.get());
        }
    }
}
