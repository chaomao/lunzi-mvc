package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public class IntegerTransformer implements Transformer {

    @Override
    public Object transform(Class modelClass, String parameterKey, Map<String, String[]> map) {
        return Integer.valueOf(map.get(parameterKey)[0]);
    }
}
