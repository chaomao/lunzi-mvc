package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public class StringTransformer implements Transformer {
    @Override
    public Object transform(Class modelClass, String parameterKey, Map<String, String[]> map) {
        return map.get(parameterKey)[0];
    }
}
