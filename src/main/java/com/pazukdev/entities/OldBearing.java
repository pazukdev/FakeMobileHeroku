package com.pazukdev.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BEARINGS")
public class OldBearing extends AbstractEntity {

    @Column(name = "NUMBER_OF_ORIGINAL")
    private String numberOfOriginal;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MAJOR_LOCATION")
    private String majorLocation;

    @Column(name = "QUANTITY")
    private int quantity;


    @Override
    public String toString() {
        return "\nNumber of original " + numberOfOriginal
                + "\nType " + type
                + "\nMajor location " + majorLocation
                + "\nQuantity " + quantity
                + "\n";
    }


    public String getNumberOfOriginal() {
        return numberOfOriginal;
    }

    public void setNumberOfOriginal(String numberOfOriginal) {
        this.numberOfOriginal = numberOfOriginal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajorLocation() {
        return majorLocation;
    }

    public void setMajorLocation(String majorLocation) {
        this.majorLocation = majorLocation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
