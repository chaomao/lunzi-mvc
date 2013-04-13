package com.thoughtworks.mvc.view.resolver;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import com.thoughtworks.mvc.model.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MustacheViewResolver implements ViewResolver {

    @Override
    public void render(ModelAndView modelAndView, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println(getResult(modelAndView));
        } catch (IOException ignored) {
        }
    }

    private String getResult(ModelAndView modelAndView) {
        FileReader reader = null;
        try {
            String viewPath = String.format("src/main/views/%s.mus", modelAndView.getViewName());
            reader = new FileReader(viewPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Template template = Mustache.compiler().compile(reader);
        return template.execute(modelAndView.getModelMap());
    }
}
