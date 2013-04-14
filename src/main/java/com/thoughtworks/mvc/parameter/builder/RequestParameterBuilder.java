package com.thoughtworks.mvc.parameter.builder;

import com.thoughtworks.mvc.IOCContainer;
import com.thoughtworks.mvc.annotations.RequestParameter;
import com.thoughtworks.mvc.parameter.TransformerChooser;
import com.thoughtworks.mvc.parameter.transformers.Transformer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

public class RequestParameterBuilder implements ParameterBuilder {

    private final Class parameterType;
    private final Annotation[] parameterAnnotation;
    private final TransformerChooser chooser = IOCContainer.getInstance().get(TransformerChooser.class);

    public RequestParameterBuilder(Class parameterType, Annotation[] parameterAnnotation) {
        this.parameterType = parameterType;
        this.parameterAnnotation = parameterAnnotation;
    }

    @Override
    public Object build(HttpServletRequest request) {
        RequestParameter annotation = (RequestParameter) parameterAnnotation[0];
        Transformer transformer = chooser.choose(parameterType);
        return transformer.transform(annotation.value(), request.getParameterMap());
    }
}
