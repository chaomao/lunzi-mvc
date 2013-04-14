package models;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String name;
    private List<Author> authors = new ArrayList<>();

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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
