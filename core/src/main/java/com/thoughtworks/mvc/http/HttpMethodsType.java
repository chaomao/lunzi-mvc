package com.thoughtworks.mvc.http;

import java.lang.reflect.Method;

public enum HttpMethodsType {
    Post, Get;

    public static HttpMethodsType getHttpMethodsType(Method input) {
        return hasNoPostAnnotation(input) ?
                Get : Post;
    }

    private static boolean hasNoPostAnnotation(Method input) {
        return input.getAnnotation(com.thoughtworks.mvc.annotations.Post.class) == null;
    }
}
