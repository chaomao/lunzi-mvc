package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public class EnumTransformer implements Transformer {
    @Override
    public Object transform(Class modelClass, String parameterKey, Map<String, String[]> map) {
        return Enum.valueOf(modelClass, map.get(parameterKey)[0]);
    }
}
