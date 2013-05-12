package com.thoughtworks.mvc.parameter.transformers;

import com.thoughtworks.mvc.parameter.TransformerChooser;

import java.beans.IntrospectionException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class CustomizeTransformer implements Transformer {

    @Override
    public Object transform(Class modelClass, String parameterKey, Map<String, String[]> map) {
        try {
            Constructor<?> constructor = modelClass.getConstructor();
            Object target = constructor.newInstance();
            return setObject(modelClass, parameterKey, map, target);
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException | IntrospectionException ignored) {
            throw new RuntimeException();
        }
    }

    private Object setObject(Class type, String parameterKey, Map<String, String[]> map, Object target) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            field.set(target, getSetterValue(parameterKey, map, field));
        }
        return target;
    }

    private Object getSetterValue(String name, Map<String, String[]> map, Field field) {
        String parameterKey = String.format("%s.%s", name, field.getName());
        Class<?> parameterType = field.getType();
        Transformer transformer = new TransformerChooser().choose(parameterType);
        return parameterType.equals(List.class) ?
                transformList(map, field, parameterKey, transformer) :
                transformer.transform(parameterType, parameterKey, map);
    }

    private Object transformList(Map<String, String[]> map, Field field, String removeLastChar, Transformer transformer) {
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        Class parameterType = (Class<?>) genericType.getActualTypeArguments()[0];
        removeLastChar = removeLastChar.substring(0, removeLastChar.length() - 1);
        return transformer.transform(parameterType, removeLastChar, map);
    }
}
