package app.controllers;

import app.models.Author;
import app.models.Book;
import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.annotations.Post;
import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.model.ModelMap;

public class BookController {

    @Path("/book/sample")
    public ModelMap sample() {
        ModelMap modelMap = new ModelMap();
        Book book = new Book("How to new bee");
        book.addAuthor(new Author("Mao Chao"));
        book.addAuthor(new Author("Wang Xiaofeng"));
        modelMap.put("book", book);
        return modelMap;
    }

    @Path("/book/new")
    public void newBook() {
    }

    @Path("/book/create")
    @Post
    public ModelMap create(@RequestParameter("book") Book book) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("book", book);
        return modelMap;
    }
}
