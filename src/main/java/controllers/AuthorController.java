package controllers;

import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.model.ModelMap;
import models.Author;

public class AuthorController {

    @Path("/author/list")
    public ModelMap list() {
        ModelMap modelMap = new ModelMap();
        modelMap.put("author", new Author("Mao Chao"));
        return modelMap;
    }
}
