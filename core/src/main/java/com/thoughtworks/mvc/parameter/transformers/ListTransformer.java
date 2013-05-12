package com.thoughtworks.mvc.parameter.transformers;

import com.thoughtworks.mvc.parameter.TransformerChooser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTransformer implements Transformer {


    @Override
    public Object transform(Class modelClass, String parameterKey, Map<String, String[]> parameterMap) {
        ArrayList objects = new ArrayList();
        List<Map<String, String[]>> modelData = extractFromParameterMap(modelClass, parameterKey, parameterMap);
        for (Map<String, String[]> m : modelData) {
            Transformer transformer = new TransformerChooser().choose(modelClass);
            objects.add(transformer.transform(modelClass, modelClass.getSimpleName(), m));
        }
        return objects;
    }

    private List<Map<String, String[]>> extractFromParameterMap(Class modelClass, String parameterKey, Map<String, String[]> parameterMap) {
        List<Map<String, String[]>> modelData = new ArrayList<>();
        for (Field field : modelClass.getDeclaredFields()) {
            String key = parameterKey + "[]." + field.getName();
            String[] values = parameterMap.get(key);
            initModelDataIfNecessary(modelData, values.length);
            for (int i = 0; i < values.length; i++) {
                String newKey = modelClass.getSimpleName() + "." + field.getName();
                modelData.get(i).put(newKey, new String[]{values[i]});
            }
        }
        return modelData;
    }

    private void initModelDataIfNecessary(List<Map<String, String[]>> modelData, int length) {
        if (modelData.isEmpty()) {
            for (int i = 0; i < length; i++) {
                modelData.add(new HashMap<String, String[]>());
            }
        }
    }
}
