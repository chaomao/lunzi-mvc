package controllers;

import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.annotations.Post;
import com.thoughtworks.mvc.annotations.ViewModel;

public class PostController {
    @Path("/submit")
    @Post
    @ViewModel(name = "content")
    public String submit() {
        return "You have submit successful";
    }
}
