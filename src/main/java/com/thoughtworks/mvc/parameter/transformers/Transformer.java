package com.thoughtworks.mvc.parameter.transformers;

import java.util.Map;

public interface Transformer<T> {
    T transform(String name, Map<String, String[]> map);

}
