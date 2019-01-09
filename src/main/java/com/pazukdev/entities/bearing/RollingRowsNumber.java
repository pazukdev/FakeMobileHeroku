package com.pazukdev.entities.bearing;

public enum RollingRowsNumber {

    SINGLE_ROW, DOUBLE_ROW, FOUR_ROW;

    @Override
    public String toString() {
        String returnValue = super.toString();
        if (returnValue.equals("FOUR")) {
            returnValue = returnValue.replace("FOUR", "4"); // "FOUR_ROW" -> "4_ROW"
        }
        return returnValue.toLowerCase().replace("_", "-"); // "4_ROW" -> "4-row"
    }
}
