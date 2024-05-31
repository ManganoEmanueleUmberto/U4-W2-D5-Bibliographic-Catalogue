package emanuele_mangano.Entities;

public class Book extends Catalogue {
    private String author;
    private String genre;

    public Book(String author, String genre, String ISBN, String title, int publishingYear, int pagesNumber) {
        super(ISBN, title, publishingYear, pagesNumber);
        this.author = author;
        this.genre = genre;
    }


    public String toString() {
        return super.toString() +
                ", author: " + author +
                ", genre: " + genre;
    }

    public String getAuthor() {
        return author;
    }
}
