package com.thoughtworks.mvc.view.resolver;

import com.thoughtworks.mvc.model.ModelAndView;

import javax.servlet.http.HttpServletResponse;

public interface ViewResolver {

    void render(ModelAndView modelAndView, HttpServletResponse response);
}
