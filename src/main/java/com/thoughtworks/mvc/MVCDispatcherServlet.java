package com.thoughtworks.mvc;

import com.thoughtworks.mvc.controllers.HelloWorldController;
import com.thoughtworks.mvc.http.HttpMethodsType;
import com.thoughtworks.mvc.resolver.SimpleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.thoughtworks.mvc.http.HttpMethodsType.Get;
import static com.thoughtworks.mvc.http.HttpMethodsType.Post;

public class MVCDispatcherServlet extends HttpServlet {

    private final SimpleResolver simpleResolver = new SimpleResolver();

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, Get);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, Post);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response, HttpMethodsType type) throws IOException {
        HelloWorldController controller = new HelloWorldController();
        String hello = controller.hello();
        simpleResolver.render(hello, response);
    }

}


