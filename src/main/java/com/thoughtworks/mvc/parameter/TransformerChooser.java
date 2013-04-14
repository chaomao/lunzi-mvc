package com.thoughtworks.mvc.parameter;

import com.thoughtworks.mvc.parameter.transformers.IntegerTransformer;
import com.thoughtworks.mvc.parameter.transformers.StringTransformer;
import com.thoughtworks.mvc.parameter.transformers.Transformer;

import java.util.HashMap;

public class TransformerChooser {

    private HashMap<Class, Transformer> transformerHashMap = new HashMap<>();

    public TransformerChooser() {
        init();
    }

    private void init() {
        transformerHashMap.put(Integer.class, new IntegerTransformer());
        transformerHashMap.put(int.class, new IntegerTransformer());
        transformerHashMap.put(String.class, new StringTransformer());
    }

    public Transformer choose(Class type) {
        return transformerHashMap.get(type);
    }
}
