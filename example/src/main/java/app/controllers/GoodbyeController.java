package app.controllers;

import com.thoughtworks.mvc.annotations.Path;
import app.services.Service;

public class GoodbyeController {
    private Service service;

    public GoodbyeController(Service service) {
        this.service = service;
    }

    @Path("/goodbye")
    public String goodbye() {
        return service.serve() + " Goodbye Mao Chao";
    }
}
