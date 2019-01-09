package com.pazukdev.entities.objectanalyzer.version2;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Siarhei Sviarkaltsau
 */
@Getter
public class OutputFilter {

    enum FilterOption {
        CONSTANTS,
        LOGGER,
        NULL,
        NOT_NULL,
        WITHOUT_SUPER
    }

    private Set<FilterOption> options;


    private OutputFilter(final FilterOption... filterOptions) {
        options = new HashSet<>(Arrays.asList(filterOptions));
    }

    public static OutputFilter createDefaultFilter() {
        return new OutputFilter(FilterOption.LOGGER, FilterOption.CONSTANTS);
    }

    public static OutputFilter createNullFilter() {
        return new OutputFilter(FilterOption.NULL);
    }

    public static OutputFilter createNotNullFilter() {
        return new OutputFilter(FilterOption.NOT_NULL);
    }

    public static OutputFilter createWithoutSuperFilter() {
        return new OutputFilter(FilterOption.WITHOUT_SUPER);
    }

    public static OutputFilter createCustomFilter(final FilterOption... options) {
        return new OutputFilter(options);
    }

}













