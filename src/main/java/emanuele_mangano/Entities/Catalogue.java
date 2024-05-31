package emanuele_mangano.Entities;

public abstract class Catalogue {
    private String ISBN;
    private String title;
    private int publishingYear;
    private int pagesNumber;

    public Catalogue(String ISBN, String title, int publishingYear, int pagesNumber) {
        this.ISBN = ISBN;
        this.title = title;
        this.publishingYear = publishingYear;
        this.pagesNumber = pagesNumber;
    }


    public String toString() {
        return "ISBN:" + ISBN +
                ", title: " + title +
                ", publishingYear: " + publishingYear +
                ", pagesNumber: " + pagesNumber;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public String getISBN() {
        return ISBN;
    }
}
