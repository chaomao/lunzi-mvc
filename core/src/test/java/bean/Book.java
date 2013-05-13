package bean;

import java.util.ArrayList;

public class Book {
    private String name;
    private ArrayList<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
}
