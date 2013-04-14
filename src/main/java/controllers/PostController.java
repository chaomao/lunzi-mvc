package controllers;

import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.annotations.Post;
import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.model.ModelMap;

public class PostController {

    @Path("/post/new")
    public void newForm() {
    }

    @Path("/post/submit")
    @Post
    public ModelMap submit(@RequestParameter("name") String name) {
        ModelMap modelMap = new ModelMap();
        System.out.println(name);
        modelMap.put("name", name);
        return modelMap;
    }
}
