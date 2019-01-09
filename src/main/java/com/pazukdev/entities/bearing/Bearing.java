package com.pazukdev.entities.bearing;

import com.pazukdev.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Bearing extends AbstractEntity {

    private static final String VALUE_SEPARATOR = ": ";

    public static class FeatureDescription {
        public static final String ORIGINAL_PART_NUMBER = "original part number";
        public static final String TYPE = "type";
        public static final String ROLLING_ELEMENT = "rolling element";
        public static final String ROLLER_TYPE = "roller type";
        public static final String ENCLOSURE = "enclosure";
        public static final String ROWS_NUMBER = "rows number";
        public static final String CAGE = "cage";
        public static final String CAGE_MATERIAL = "cage material";
        public static final String INNER_DIMENSION = "inner dimension";
        public static final String OUTER_DIMENSION = "outer dimension";
        public static final String WIDTH = "width";
        public static final String ROLLING_ELEMENTS_NUMBER = "rolling elements number";
        public static final String WEIGHT = "weight";
        public static final String DYNAMIC = "dynamic";
    }

    public enum Type {
        DEEPGROOVE, THRUST, ANGULARCONTACT;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum Enclosure {
        OPEN, SEAL, SHIELD;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private String originalPartNumber;
    private Type type;
    private RollingElement rollingElement;
    private Enclosure enclosure;
    private RollingRowsNumber rollingRowsNumber;
    private Cage cage;

    private int innerDimension;
    private int outerDimension;
    private int width;

    // additional data
    private int rollingElementsNumber;
    private int weight;
    private int dynamic;

    /*@Override
    public String toString() {
        //return this.getOriginalPartNumber();
        return super.toString();
    }*/

    public String getReportStringWithReplasedValues() { // TODO: write me!
        return null;
    }

    public String getReportString(final boolean includeNull) {
        return getReportString(includeNull, true);
    }

    public String getReportString(final boolean includeNull, final boolean includeZeroValue) {
        return getReportString(includeNull, includeZeroValue, getReportList());
    }

    private String getReportString(final boolean includeNullValue,
                                   final boolean includeZeroValue,
                                   final List<String> reportList) {
        String reportString = "";
        for (String feature : reportList) {
            if (featureContains(feature, "null") && !includeNullValue) {
                continue;
            }
            if (featureContains(feature, "0") && !includeZeroValue) {
                continue;
            }

            reportString = reportString + "\n" + feature;
        }

        return reportString;
    }

    private boolean featureContains(final String feature, final String valueNotToContain) {
        String featureValue = Arrays.asList(feature.split(VALUE_SEPARATOR)).get(1);
        return featureValue.equals(valueNotToContain);
    }

    private List<String> getReportList() {
        final List<String> reportList = new ArrayList<>();

        reportList.add(featureToString(FeatureDescription.ORIGINAL_PART_NUMBER, originalPartNumber));
        reportList.add(featureToString(FeatureDescription.TYPE, type));
        reportList.add(featureToString(FeatureDescription.ROLLING_ELEMENT, rollingElement));
        if (rollingElement != null) {
            reportList.add(featureToString(FeatureDescription.ROLLER_TYPE, rollingElement.getRollerType()));
        }
        reportList.add(featureToString(FeatureDescription.ENCLOSURE, enclosure));
        reportList.add(featureToString(FeatureDescription.ROWS_NUMBER, rollingRowsNumber));
        reportList.add(featureToString(FeatureDescription.CAGE, cage));
        if (cage != null) {
            reportList.add(featureToString(FeatureDescription.CAGE_MATERIAL, cage.getMaterial()));
        }
        reportList.add(featureToString(FeatureDescription.INNER_DIMENSION, innerDimension));
        reportList.add(featureToString(FeatureDescription.OUTER_DIMENSION, outerDimension));
        reportList.add(featureToString(FeatureDescription.WIDTH, width));
        reportList.add(featureToString(FeatureDescription.ROLLING_ELEMENTS_NUMBER, rollingElementsNumber));
        reportList.add(featureToString(FeatureDescription.WEIGHT, weight));
        reportList.add(featureToString(FeatureDescription.DYNAMIC, dynamic));

        return reportList;
        
    }

    private String featureToString(final String parameter, final Object object) {
        return String.format("%s" + VALUE_SEPARATOR + "%s", parameter, object);
    }

}
