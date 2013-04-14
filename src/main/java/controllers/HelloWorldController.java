package controllers;

import com.thoughtworks.mvc.annotations.Path;

public class HelloWorldController {

    @Path("/hello")
    public String hello() {
        return "Mao Chao";
    }
}
