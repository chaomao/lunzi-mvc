package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public class StringTransformer implements Transformer {
    @Override
    public <T> T transform(Class<T> type, String parameterKey, Map<String, String[]> map) {
        return (T) map.get(parameterKey)[0];
    }
}
