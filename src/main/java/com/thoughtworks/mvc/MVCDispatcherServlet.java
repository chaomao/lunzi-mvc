package com.thoughtworks.mvc;

import com.google.common.base.Predicate;
import com.thoughtworks.mvc.action.ActionCaller;
import com.thoughtworks.mvc.action.ActionCallerFactory;
import com.thoughtworks.mvc.action.ActionCallersFactoryImpl;
import com.thoughtworks.mvc.http.HttpMethodsType;
import com.thoughtworks.mvc.model.ModelAndView;
import com.thoughtworks.mvc.parameter.ParameterAnalyzer;
import com.thoughtworks.mvc.parameter.TransformerChooser;
import com.thoughtworks.mvc.view.resolver.MustacheViewResolver;
import com.thoughtworks.mvc.view.resolver.ViewResolver;
import com.thoughtworks.row.ioc.Container;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.google.common.collect.Iterables.find;
import static com.thoughtworks.mvc.http.HttpMethodsType.Get;
import static com.thoughtworks.mvc.http.HttpMethodsType.Post;

public class MVCDispatcherServlet extends HttpServlet {

    private final ViewResolver resolver = new MustacheViewResolver();
    private final Container container = IOCContainer.getInstance();
    private ActionCallerFactory actionCallersFactory;
    private ArrayList<ActionCaller> actionCallers;

    public void init() throws ServletException {
        container.registerComponentsInPackage("app.controllers");
        container.registerComponentsInPackage("app.services");
        container.registerComponentsInPackage("com.thoughtworks.mvc.parameter.analyzer");
        container.registerComponentsInPackage("com.thoughtworks.mvc.parameter.transformers");
        container.register(ParameterAnalyzer.class, new ParameterAnalyzer());
        container.register(ActionCallerFactory.class, ActionCallersFactoryImpl.class);
        container.register(TransformerChooser.class, new TransformerChooser());
        actionCallersFactory = container.get(ActionCallerFactory.class);
        actionCallers = actionCallersFactory.createActionCallers(container.getAllClasses("app.controllers"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, Get);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response, Post);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response, final HttpMethodsType type) {
        ActionCaller actionCaller = getActionCaller(request, type);
        ModelAndView modelAndView = actionCaller.run(request);
        resolver.render(modelAndView, response);
    }

    private ActionCaller getActionCaller(final HttpServletRequest request, final HttpMethodsType type) {
        return find(actionCallers, new Predicate<ActionCaller>() {
            @Override
            public boolean apply(ActionCaller input) {
                return input.fitable(request, type);
            }
        });
    }
}


