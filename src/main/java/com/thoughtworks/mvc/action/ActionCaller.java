package com.thoughtworks.mvc.action;

import com.thoughtworks.mvc.IOCContainer;
import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.http.HttpMethodsType;
import com.thoughtworks.mvc.model.ModelAndView;
import com.thoughtworks.mvc.model.ModelMap;
import com.thoughtworks.mvc.view.resolver.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

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
            Object result = definition.action.invoke(controller, getParameters(request));
            ModelMap modelMap = createModelMap(result);
            return new ModelAndView(modelMap, getViewPath());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    private Object[] getParameters(HttpServletRequest request) {
        ArrayList<Object> objects = new ArrayList<>();
        Annotation[][] parameterAnnotations = definition.action.getParameterAnnotations();
        Class<?>[] parameterTypes = definition.action.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Annotation annotation = parameterAnnotations[i][0];
            String paraName = getParameterName(annotation, parameterTypes[i]);
            objects.add(request.getParameter(paraName));
        }
        return objects.toArray();
    }

    private String getParameterName(Annotation annotation, Class<?> parameterType) {
        if (annotation instanceof RequestParameter) {
            return ((RequestParameter) annotation).value();
        } else {
            return parameterType.getName();
        }
    }

    private ModelMap createModelMap(Object result) {
        ModelMap modelMap = new ModelMap();
        if (result instanceof String) {
            modelMap.put(definition.viewModelName, result);
        } else if (result instanceof ModelMap) {
            modelMap.putAll((Map<? extends String, ?>) result);
        }
        return modelMap;
    }

    private String getViewPath() {
        String controllerName = definition.controller.getSimpleName().replaceFirst("Controller", "");
        return String.format("%s/%s", controllerName, definition.action.getName());
    }
}
