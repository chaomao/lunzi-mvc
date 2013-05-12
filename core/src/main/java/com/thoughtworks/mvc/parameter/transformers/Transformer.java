package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public interface Transformer {
    Object transform(Class modelClass, String parameterKey, Map<String, String[]> map);
}