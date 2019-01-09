package com.pazukdev.entities.objectanalyzer.version2;

import com.pazukdev.entities.objectanalyzer.version2.OutputFilter.FilterOption;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Achtung! This class uses reflection and violate classes encapsulation. Use this class only for debug purposes.
 *
 * This class provides object state by its public method {@link #getStateReport()}
 * (or {@link #getStateReport(OutputFilter)})
 *
 * Purpose example:
 * 1. Override toString method of your class:
 * public class MyClass {
 *     @Override
 *     public String toString() {
 *         return new ObjectAnalyzer(this).getStateReport();
 *     }
 * }
 *
 * 2. In any place you need state of instance of your class:
 * log.log(Level.INFO, myClass.toString());
 *
 * 3. Get state information in console:
 * ======================
 * Passport:
 * number = FG275623
 * lastName = Cartier
 * fullName = Mr Kim Cartier
 * dateOfBirth = null
 * nationality = Monaco
 * ======================
 * Document:
 * documentType = Passport
 * dateOfIssue = 13.10.2018
 * expiryDate = 12.12.2018
 * ownerRole = director
 * ======================
 * AbstractEntity:
 * expectedStatus = ACTIVE
 * actualStatus = ACTIVE
 * ======================
 *
 * @author Siarhei Sviarkaltsau
 */
@Log
@Setter
public class ObjectAnalyzer2 implements ObjectAnalyzer {

    private static final String DEFAULT_VALUE_SEPARATOR = " = ";
    private static final String DEFAULT_LINE_SEPARATOR = "=====================";

    private OutputFilter outputFilter;
    private final Object o;
    private final Map<String, String > objectStateMap;
    private String valueSeparator;
    private String lineSeparator;


    public ObjectAnalyzer2(final Object o) {
        this.o = o;
        outputFilter = OutputFilter.createDefaultFilter();
        objectStateMap = getObjectStateMap(this.o);
        valueSeparator = DEFAULT_VALUE_SEPARATOR;
        lineSeparator = DEFAULT_LINE_SEPARATOR;
    }

    // public methods

    @Override
    public String getStateReport() {
        return getStateReport(outputFilter);
    }

    @Override
    public String getStateReport(final OutputFilter outputFilter) {
        final StringBuilder reportString = new StringBuilder();

        for (final Map.Entry entry : objectStateMap.entrySet()) {

            final String key = entry.getKey().toString();
            final String value = entry.getValue().toString();

            if (filter(outputFilter, key, value)) {
                reportString.append("\n");
                if (value.equals("instance class")) {
                    if (outputFilter.getOptions().contains(FilterOption.WITHOUT_SUPER)
                            && key.equals(o.getClass().getSuperclass().getSimpleName())) break;
                    reportString.append(lineSeparator).append("\n").append(key).append(":");
                } else reportString.append(key).append(valueSeparator).append(value);
            }
        }

        return reportString.append("\n").append(lineSeparator).toString();
    }

    // non-public methods

    private boolean filter(final OutputFilter filter,
                           final String key,
                           @NotNull final String value) {
        final Set<FilterOption> filterOptions = filter.getOptions();

        if (filterOptions.contains(FilterOption.CONSTANTS) && key.equals(key.toUpperCase())) return false;
        if (filterOptions.contains(FilterOption.LOGGER) && value.contains(".Logger@")) return false;
        if (filterOptions.contains(FilterOption.NULL) && !valueIsNull(value)) return false;
        if (filterOptions.contains(FilterOption.NOT_NULL) && valueIsNull(value)) return false;

        return true;
    }

    private boolean valueIsNull(final String value) {
        return value.equals("null") || value.equals("[]") || value.isEmpty();
    }

    private Map<String, String> getObjectStateMap(final Object o) {
        final Map<String, String> objectStateMap = new LinkedHashMap<>();

        for (final Class clazz : getObjectGenealogyList(o)) {
            objectStateMap.put(clazz.getSimpleName(), "instance class");
            for (final Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.get(o) == null) {
                        objectStateMap.put(field.getName(), "null");
                    } else {
                        objectStateMap.put(field.getName(), field.get(o).toString());
                    }
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return objectStateMap;
    }

    private List<Class> getObjectGenealogyList(final Object o) {
        final List<Class> classes = new ArrayList<>();
        Class superClass = o.getClass();
        do {
            classes.add(superClass);
            superClass = superClass.getSuperclass();
        } while (!superClass.getSimpleName().equals("Object"));
        return classes;
    }

}
