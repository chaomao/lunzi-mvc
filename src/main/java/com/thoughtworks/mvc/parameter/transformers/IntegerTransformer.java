package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public class IntegerTransformer implements Transformer<Integer> {

    @Override
    public Integer transform(String name, Map<String, String[]> map) {
        return Integer.valueOf(map.get(name)[0]);
    }
}
