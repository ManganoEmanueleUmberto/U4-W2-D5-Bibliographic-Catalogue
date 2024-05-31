package emanuele_mangano;

import com.github.javafaker.Faker;
import emanuele_mangano.Entities.Book;
import emanuele_mangano.Entities.Catalogue;
import emanuele_mangano.Entities.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class Archive {

    public static void main(String[] args) {
        List<Catalogue> catalogue = new ArrayList<>();
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
            System.out.println("******** MENU ********" + System.lineSeparator() + "1. Generate one or more Books" + System.lineSeparator()
                    + "2. Generate one or more Magazines" + System.lineSeparator() + "3. Print All the Book/Magazine and Magazines " + System.lineSeparator()
                    + "4. Remove a Book/Magazine by ISBN" + System.lineSeparator() + "5. Search a Book/Magazine by ISBN " + System.lineSeparator()
                    + "6. Search a Book/Magazine by publishing year" + System.lineSeparator() + "7. Search a Book/Magazine by author " + System.lineSeparator()
                    + "0. Exit");
            try {

                choice = Integer.parseInt(sc.nextLine());

                if (choice == 0) break;

                switch (choice) {
                    case 1:
                        System.out.println(System.lineSeparator() + "How much books you wanna generate? ");
                        choiceLength = Integer.parseInt(sc.nextLine());
                        addBook(catalogue, bookSupplier, choiceLength);
                        System.out.println(choiceLength + " books successfully created");
                        break;

                    case 2:
                        System.out.println(System.lineSeparator() + "How much magazines you wanna generate? ");
                        choiceLength = Integer.parseInt(sc.nextLine());
                        addMagazine(catalogue, magazineSupplier, choiceLength);
                        System.out.println(choiceLength + " magazines successfully created");
                        break;

                    case 3:
                        System.out.println(System.lineSeparator() + "CATALOGUE ");
                        for (Catalogue book : catalogue) {
                            System.out.println(book);
                        }
                        break;

                    case 4:
                        String ISBNToRemove;
                        System.out.println(System.lineSeparator() + "Enter the ISBN you wish to remove: ");
                        ISBNToRemove = sc.nextLine();
                        removeElementByISBN(catalogue, ISBNToRemove);
                        break;

                    case 5:
                        String ISBNToSearch;
                        System.out.println(System.lineSeparator() + "Enter the ISBN you wish to search: ");
                        ISBNToSearch = sc.nextLine();
                        searchElementByISBN(catalogue, ISBNToSearch);
                        break;

                    case 6:
                        int yearToSearch;
                        System.out.println(System.lineSeparator() + "Enter the Year you wish to search: ");
                        yearToSearch = Integer.parseInt(sc.nextLine());
                        searchElementByPublishingYear(catalogue, yearToSearch);
                        break;

                    case 7:
                        String authorToSearch;
                        System.out.println(System.lineSeparator() + "Enter the author you wish to search: ");
                        authorToSearch = sc.nextLine();
                        searchElementByAuthor(catalogue, authorToSearch);
                        break;

                    default:
                        System.out.println("You have entered a wrong number! Retry");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("You must enter a number! ");
            }
        }

    }

    public static void addBook(List<Catalogue> catalogue, Supplier<Book> bookSupplier, int length) {
        for (int i = 0; i < length; i++) {
            catalogue.add(bookSupplier.get());
        }
    }

    public static void addMagazine(List<Catalogue> catalogue, Supplier<Magazine> magazineSupplier, int length) {
        for (int i = 0; i < length; i++) {
            catalogue.add(magazineSupplier.get());
        }
    }

    public static void removeElementByISBN(List<Catalogue> catalogue, String ISBNToRemove) {
        if (catalogue.removeIf(element -> element.getISBN().equals(ISBNToRemove)))
            System.out.println("Element with ISBN " + ISBNToRemove + " successfully eliminated");
        else System.out.println("Element with ISBN " + ISBNToRemove + " not found");
    }

    public static void searchElementByISBN(List<Catalogue> catalogue, String ISBNToSearch) {
        List<Catalogue> searchList = new ArrayList<>();
        searchList = catalogue.stream().filter(element -> element.getISBN().equals(ISBNToSearch)).toList();
        if (searchList.isEmpty())
            System.out.println("The are no books with " + ISBNToSearch + " ISBN in the archive");
        else System.out.println(searchList);
    }

    public static void searchElementByPublishingYear(List<Catalogue> catalogue, int yearToSearch) {
        List<Catalogue> searchList = new ArrayList<>();
        searchList = catalogue.stream().filter(element -> element.getPublishingYear() == yearToSearch).toList();
        if (searchList.isEmpty())
            System.out.println("The are no books published in " + yearToSearch + " in the archive");
        else System.out.println(searchList);
    }

    public static void searchElementByAuthor(List<Catalogue> catalogue, String author) {
        List<Catalogue> searchList = new ArrayList<>();
        searchList = catalogue.stream()
                .filter(element -> element instanceof Book && ((Book) element).getAuthor().equals(author))
                .toList();
        if (searchList.isEmpty())
            System.out.println("The are no books from " + author + " in the archive");
        else System.out.println(searchList);

    }

}
