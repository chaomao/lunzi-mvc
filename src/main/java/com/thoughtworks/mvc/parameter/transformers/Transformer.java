package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public interface Transformer {
    <T> T transform(Class<T> type, String parameterKey, Map<String, String[]> map);
}
