package com.pazukdev.entities.bearing;

import lombok.Getter;
import lombok.Setter;

public enum RollingElement {

    BALL, ROLLER;

    @Getter
    @Setter
    private RollerType rollerType;

    public enum RollerType {
        CYLINDRICAL, NEEDLE, TAPERED, SPHERICAL
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}


