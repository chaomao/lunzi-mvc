package com.thoughtworks.mvc.resolver;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SimpleResolver implements Resolver {

    @Override
    public void render(String content, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println(getResult(content));
        } catch (IOException ignored) {
        }
    }

    private String getResult(String content) {
        FileReader reader = null;
        try {
            reader = new FileReader("src/main/java/com/thoughtworks/mvc/views/hello.mus");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Template template = Mustache.compiler().compile(reader);
        Map<String, String> data = new HashMap<String, String>();
        data.put("content", content);
        return template.execute(data);
    }
}
