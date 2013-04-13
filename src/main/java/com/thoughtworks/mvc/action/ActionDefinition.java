package com.thoughtworks.mvc.action;

import com.thoughtworks.mvc.http.HttpMethodsType;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionDefinition {
    public HttpMethodsType httpMethodType;
    public String path;
    public String viewModelName;
    public Class<?> controller;
    public Method action;

    public ActionDefinition(HttpMethodsType httpMethodType, String path, String viewModelName, Class<?> controller, Method action) {
        this.httpMethodType = httpMethodType;
        this.path = path;
        this.viewModelName = viewModelName;
        this.controller = controller;
        this.action = action;
    }

    public boolean fitable(HttpServletRequest request, HttpMethodsType type) {
        if (!httpMethodType.equals(type)) {
            return false;
        }
        Pattern pattern = Pattern.compile("(?i)" + path + "$");
        Matcher matcher = pattern.matcher(request.getRequestURI());
        return matcher.find();
    }
}
