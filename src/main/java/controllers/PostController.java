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
    public ModelMap submit(@RequestParameter("name") String name, @RequestParameter("age") int age) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("name", name);
        modelMap.put("age", age);
        return modelMap;
    }
}
