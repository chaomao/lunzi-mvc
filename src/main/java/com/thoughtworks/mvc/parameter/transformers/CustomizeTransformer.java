package com.thoughtworks.mvc.parameter.transformers;

import com.thoughtworks.mvc.parameter.TransformerChooser;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class CustomizeTransformer implements Transformer {
    TransformerChooser chooser = new TransformerChooser();

    @Override
    public <T> T transform(Class<T> type, String parameterKey, Map<String, String[]> map) {
        Constructor<?> constructor = type.getConstructors()[0];
        try {
            Object target = constructor.newInstance();
            for (PropertyDescriptor descriptor : Introspector.getBeanInfo(type).getPropertyDescriptors()) {
                Method setter = descriptor.getWriteMethod();
                if (setter != null) {
                    setter.invoke(target, getSetterValue(parameterKey, map, descriptor));
                }
            }
            return (T) target;
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | IntrospectionException ignored) {
            throw new RuntimeException();
        }
    }

    private Object getSetterValue(String name, Map<String, String[]> map, PropertyDescriptor descriptor) {
        String fieldName = descriptor.getName();
        String parameterKey = String.format("%s.%s", name, fieldName);
        Class<?> parameterType = descriptor.getPropertyType();
        return chooser.choose(parameterType).transform(parameterType, parameterKey, map);
    }
}
