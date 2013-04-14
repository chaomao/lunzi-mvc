package controllers;

import com.google.common.collect.Lists;
import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.model.ModelMap;
import models.Author;

public class AuthorController {

    @Path("/author/sample")
    public ModelMap sample() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("author", new Author("Mao Chao"));
        return modelMap;
    }

    @Path("/author/list")
    public ModelMap list() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("authors", Lists.newArrayList(new Author("Mao Chao"), new Author("Wang Xiaofeng")));
        return modelMap;
    }

    @Path("/author/new")
    public void newAuthor() {
    }

    @Path("/author/create")
    public ModelMap create(@RequestParameter("author") Author author) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("author", author);
        return modelMap;
    }
}
