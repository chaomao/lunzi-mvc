package com.thoughtworks.mvc.parameter.builder;

import javax.servlet.http.HttpServletRequest;

public interface ParameterBuilder {
    Object build(HttpServletRequest request);
}
