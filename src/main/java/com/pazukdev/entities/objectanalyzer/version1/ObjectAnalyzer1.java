package com.pazukdev.entities.objectanalyzer.version1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ObjectAnalyzer1 {

    private static final String VALUE_SEPARATOR = " = ";

    private boolean constantFilter;
    private Map<String, String > objectStateMap;


    public ObjectAnalyzer1(Object o) {
        objectStateMap = getObjectStateMap(o);
    }

    public String getStateReport(final boolean constantFilter) {
        this.constantFilter = constantFilter;
        final StringBuilder reportString = new StringBuilder();
        objectStateMap.forEach((key, value) -> {
            if (!filterConstant(key)) {
                reportString.append("\n").append(key).append(VALUE_SEPARATOR).append(value);
            }
        });
        return reportString.toString();
    }

    private boolean filterConstant(String key) {
        if (constantFilter) {
            return key.equals(key.toUpperCase());
        } else return false;
    }

    private Map<String, String> getObjectStateMap(Object o) {
        Map<String, String> objectStateMap = new LinkedHashMap<>();

        for (Class clazz : getObjectGenealogyList(o)) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    String value = null;
                    if (field.get(o) != null) value = field.get(o).toString();
                    objectStateMap.put(field.getName(), value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return objectStateMap;
    }

    private List<Class> getObjectGenealogyList(Object o) {
        List<Class> classes = new ArrayList<>();
        Class superClass = o.getClass();
        do {
            classes.add(superClass);
            superClass = superClass.getSuperclass();
        } while (!superClass.getSimpleName().equals("Object"));
        return classes;
    }

}
