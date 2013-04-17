package app.controllers;

import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.model.ModelMap;
import app.models.Author;
import app.models.Book;


//don't implement list author, so comment book controller first
public class BookController {

//    @Path("/book/sample")
    public ModelMap sample() {
        ModelMap modelMap = new ModelMap();
        Book book = new Book("How to new bee");
        book.addAuthor(new Author("Mao Chao"));
        book.addAuthor(new Author("Wang Xiaofeng"));
        modelMap.put("book", book);
        return modelMap;
    }

//    @Path("/book/new")
    public void newBook() {
    }

//    @Path("/book/create")
//    @Post
    public ModelMap create(@RequestParameter("book") Book book) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("book", book);
        return modelMap;
    }
}
