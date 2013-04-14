package com.thoughtworks.mvc.parameter;

import com.thoughtworks.mvc.action.ActionDefinition;
import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.parameter.builder.ParameterBuilder;
import com.thoughtworks.mvc.parameter.builder.RequestParameterBuilder;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ParameterAnalyzer {

    public List<ParameterBuilder> analyzeParameters(ActionDefinition definition) {
        ArrayList<ParameterBuilder> parameterBuilders = new ArrayList<>();
        Annotation[][] parameterAnnotations = definition.action.getParameterAnnotations();
        Class<?>[] parameterTypes = definition.action.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterAnnotations[i][0] instanceof RequestParameter) {
                parameterBuilders.add(new RequestParameterBuilder(parameterTypes[i], parameterAnnotations[i]));
            }
        }
        return parameterBuilders;
    }
}
