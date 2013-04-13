package com.thoughtworks.mvc;

import org.junit.Test;

public class MVCDispatcherServletTest {
    @Test
    public void testInit() throws Exception {
        MVCDispatcherServlet mvcDispatcherServlet = new MVCDispatcherServlet();
        mvcDispatcherServlet.init();
    }
}
