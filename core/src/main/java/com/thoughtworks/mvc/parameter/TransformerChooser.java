package com.thoughtworks.mvc.parameter;

import com.thoughtworks.mvc.parameter.transformers.*;

import java.util.ArrayList;
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
        transformerHashMap.put(ArrayList.class, new ListTransformer());
    }

    public Transformer choose(Class type) {
        return transformerHashMap.containsKey(type) ?
                transformerHashMap.get(type) :
                new CustomizeTransformer();
    }
}
