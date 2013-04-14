package controllers;

import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.model.ModelMap;
import models.Author;
import models.Book;


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
}
