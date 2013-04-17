package com.thoughtworks.mvc.action;

import com.google.common.base.Function;
import com.thoughtworks.mvc.IOCContainer;
import com.thoughtworks.mvc.http.HttpMethodsType;
import com.thoughtworks.mvc.model.ModelAndView;
import com.thoughtworks.mvc.model.ModelMap;
import com.thoughtworks.mvc.parameter.builder.ParameterBuilder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Iterables.transform;

public class ActionCaller {
    private final ActionDefinition definition;
    private final List<ParameterBuilder> parameterBuilders;

    public ActionCaller(ActionDefinition definition, List<ParameterBuilder> parameterBuilders) {
        this.definition = definition;
        this.parameterBuilders = parameterBuilders;
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

    public Object[] getParameters(final HttpServletRequest request) {
        return toArray(transform(parameterBuilders, new Function<ParameterBuilder, Object>() {
            @Override
            public Object apply(ParameterBuilder input) {
                return input.build(request);
            }
        }), Object.class);
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
