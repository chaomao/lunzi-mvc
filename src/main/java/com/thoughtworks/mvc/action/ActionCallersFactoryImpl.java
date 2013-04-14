package com.thoughtworks.mvc.action;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.thoughtworks.mvc.annotations.Path;
import com.thoughtworks.mvc.annotations.ViewModel;
import com.thoughtworks.mvc.http.HttpMethodsType;
import com.thoughtworks.mvc.parameter.ParameterAnalyzer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;

public class ActionCallersFactoryImpl implements ActionCallerFactory {

    private ParameterAnalyzer analyzer;

    public ActionCallersFactoryImpl(ParameterAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Override
    public ArrayList<ActionCaller> createActionCallers(Iterable<Class> controllerClasses) {
        ArrayList<ActionCaller> actionCallers = new ArrayList<>();
        for (Class controller : controllerClasses) {
            actionCallers.addAll(create(controller));
        }
        return actionCallers;
    }

    private List<ActionCaller> create(final Class controller) {
        Iterable<Method> actions = filter(Arrays.asList(controller.getMethods()), new Predicate<Method>() {
            @Override
            public boolean apply(Method method) {
                return method.getAnnotation(Path.class) != null;
            }
        });

        Iterable<ActionDefinition> actionDefinitions = transform(actions, new Function<Method, ActionDefinition>() {
            @Override
            public ActionDefinition apply(Method input) {
                HttpMethodsType type = HttpMethodsType.getHttpMethodsType(input);
                String path = input.getAnnotation(Path.class).value();
                return new ActionDefinition(type, path, getViewModelName(input),
                        controller, input);
            }
        });

        return Lists.newArrayList(transform(actionDefinitions, new Function<ActionDefinition, ActionCaller>() {
            @Override
            public ActionCaller apply(ActionDefinition input) {
                return new ActionCaller(input, analyzer.analyzeParameters(input));
            }
        }));
    }

    private String getViewModelName(Method input) {
        ViewModel annotation = input.getAnnotation(ViewModel.class);
        return annotation == null ? "model" : annotation.name();
    }
}
