package app.controllers;

import app.models.SimpleBook;
import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.annotations.Post;
import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.model.ModelMap;

public class SimpleBookController {

    @Path("/simple-book/new")
    public void newSimpleBook() {
    }

    @Path("/simple-book/create")
    @Post
    public ModelMap create(@RequestParameter("simpleBook") SimpleBook simpleBook) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("simpleBook", simpleBook);
        return modelMap;
    }
}
