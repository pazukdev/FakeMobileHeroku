package com.pazukdev.entities.bearing;

import lombok.Getter;
import lombok.Setter;

public enum Cage {

    SHEET_METAL, BRASS, POLYAMIDE, NO_CAGE;

    @Setter
    @Getter
    public String material;

    @Override
    public String toString() {
        return super.toString().toLowerCase().replace("_", " "); // "NO_CAGE" -> "no cage"
    }
}
