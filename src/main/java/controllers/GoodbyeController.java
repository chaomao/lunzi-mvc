package controllers;

import com.thoughtworks.mvc.annotations.Path;

public class GoodbyeController {

    @Path("/goodbye")
    public String goodbye() {
        return "Goodbye Mao Chao";
    }
}
