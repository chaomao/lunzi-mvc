package com.thoughtworks.mvc.resolver;

import javax.servlet.http.HttpServletResponse;

public interface Resolver {
    void render(String content, HttpServletResponse response);
}
