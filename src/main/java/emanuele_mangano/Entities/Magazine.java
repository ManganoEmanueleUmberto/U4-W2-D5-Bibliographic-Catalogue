package emanuele_mangano.Entities;

public class Magazine extends Catalogue {
    String recurrence;

    public Magazine(String ISBN, String title, int publishingYear, int pagesNumber, String recurrence) {
        super(ISBN, title, publishingYear, pagesNumber);
        this.recurrence = recurrence;
    }

    @Override
    public String toString() {
        return super.toString() + ", recurrence: " + recurrence;
    }
}
