package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public class StringTransformer implements Transformer<String> {
    @Override
    public String transform(String name, Map<String, String[]> map) {
        return map.get(name)[0];
    }
}
