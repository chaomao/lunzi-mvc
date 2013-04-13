package com.thoughtworks.mvc;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;

public class ControllerParser {

    public ArrayList<Class> parse(String controllerDir) {
        Iterable<File> filterByFileName = filter(Arrays.asList(new File(controllerDir).listFiles()), new Predicate<File>() {
            @Override
            public boolean apply(File input) {
                return input.getName().endsWith("Controller.java");
            }
        });
        return Lists.newArrayList(transform(filterByFileName, new Function<File, Class>() {
            @Override
            public Class apply(File input) {
                try {
                    return Class.forName("controllers." + getControllerName(input));
                } catch (ClassNotFoundException ignored) {
                }
                throw new ClassCastException();
            }
        }));
    }

    private String getControllerName(File file) {
        return file.getName().substring(0, file.getName().length() - 5);
    }
}
