package com.thoughtworks.mvc.action;

import com.thoughtworks.mvc.IOCContainer;
import com.thoughtworks.mvc.http.HttpMethodsType;
import com.thoughtworks.mvc.model.ModelAndView;
import com.thoughtworks.mvc.model.ModelMap;
import com.thoughtworks.mvc.view.resolver.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class ActionCaller {
    private final ActionDefinition definition;
    private ViewResolver resolver;

    public ActionCaller(ActionDefinition definition, ViewResolver resolver) {
        this.definition = definition;
        this.resolver = resolver;
    }

    public boolean fitable(HttpServletRequest request, HttpMethodsType type) {
        return definition.fitable(request, type);
    }

    public ModelAndView run(HttpServletRequest request) {
        try {
            Object controller = IOCContainer.getInstance().get(definition.controller);
            Object result = definition.action.invoke(controller);
            ModelMap modelMap = new ModelMap();
            modelMap.put(definition.viewModelName, result);
            return new ModelAndView(modelMap, getViewPath());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    private String getViewPath() {
        String controllerName = definition.controller.getSimpleName().replaceFirst("Controller", "");
        return String.format("%s/%s", controllerName, definition.action.getName());
    }
}
