package com.pazukdev.services;

import com.pazukdev.entities.bearing.Bearing;
import com.pazukdev.entities.bearing.Cage;
import com.pazukdev.entities.bearing.RollingElement;
import com.pazukdev.entities.bearing.RollingRowsNumber;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Log
public class BearingService {

    public static class Source {
        public static final String FROM_CODE = "from code";
        public static final String FROM_FILE_TXT = "from file.txt";
    }

    private static BearingService instance;

    private final HashMap<Long, Bearing> bearings = new HashMap<>();



    private BearingService() {}


    public static BearingService getInstance() {
        if (instance == null) {
            instance = new BearingService();
            instance.ensureData(Source.FROM_CODE);
        }
        return instance;
    }

    public Bearing findBearing(String originalPartNumber) {
        for (Bearing bearing : findAll()) {
            if (bearing.getOriginalPartNumber().equals(originalPartNumber)) return bearing;
        }

        return null;
    }

    public List<Bearing> findAll() {
        return new ArrayList<>(bearings.values());
    }

    public Integer count() {
        return bearings.size();
    }

    public void delete(final Bearing bearing) {
        bearings.remove(bearing.getId());
    }

    public void save(final Bearing bearing) {
        bearings.put(bearing.getId(), bearing);
    }

    public void ensureData(String fromWhatToEnsureData) {

        if (!findAll().isEmpty()) return;

        switch (fromWhatToEnsureData) {
            case Source.FROM_FILE_TXT:
                ensureDataFromTxtFile();
                break;
            case Source.FROM_CODE:
                ensureDataFromStringArray();
        }

    }

    private void ensureDataFromStringArray() {
        for (String bearingSourceString : getDataStringArray()) {
            save(getBearingFromFeaturesList(getListFromSourceString(bearingSourceString)));
        }
    }

    private List<String> getListFromSourceString(String bearingSourceString) {
        bearingSourceString = bearingSourceString.substring(2, bearingSourceString.length() - 2);
        return Arrays.asList(bearingSourceString.split(" \\| "));
    }

    private void ensureDataFromTxtFile() {
        // no implementation
    }

    private String[] getDataStringArray() {
        return new String[] { // TODO: table head
                "| 207 | DEEPGROOVE | BALL | OPEN | SINGLE_ROW | SHEET_METAL | 35 | 72 | 17 | null | null | null |"
        };
    }

    private Bearing getBearingFromFeaturesList(final List<String> bearingFeaturesList) {
        Bearing bearing = new Bearing();

        if (!bearingFeaturesList.get(0).equals("null")) {
            bearing.setOriginalPartNumber(bearingFeaturesList.get(0));
        } else {
            throw new NullPointerException(Bearing.FeatureDescription.ORIGINAL_PART_NUMBER + " cant't be null");
        }

        // TODO: write method to avoid with it !bearingFeaturesList.get(4).equals("null") every time checks

        if (!bearingFeaturesList.get(1).equals("null")) {
            bearing.setType(Bearing.Type.valueOf(bearingFeaturesList.get(1)));
        }

        if (!bearingFeaturesList.get(2).equals("null")) {
            bearing.setRollingElement(RollingElement.valueOf(bearingFeaturesList.get(2)));
        }

        if (!bearingFeaturesList.get(3).equals("null")) {
            bearing.setEnclosure(Bearing.Enclosure.valueOf(bearingFeaturesList.get(3)));
        }

        if (!bearingFeaturesList.get(4).equals("null")) {
            bearing.setRollingRowsNumber(RollingRowsNumber.valueOf(bearingFeaturesList.get(4)));
        }

        if (!bearingFeaturesList.get(5).equals("null")) {
            bearing.setCage(Cage.valueOf(bearingFeaturesList.get(5)));
        }

        // TODO: write method for getting dimensions values from | 35x72x17 | instead of | 35 | 72 | 17 |
        if (!bearingFeaturesList.get(6).equals("null")) {
            bearing.setInnerDimension(Integer.valueOf(bearingFeaturesList.get(6)));
        }

        if (!bearingFeaturesList.get(7).equals("null")) {
            bearing.setOuterDimension(Integer.valueOf(bearingFeaturesList.get(7)));
        }

        if (!bearingFeaturesList.get(8).equals("null")) {
            bearing.setWidth(Integer.valueOf(bearingFeaturesList.get(8)));
        }

        if (!bearingFeaturesList.get(9).equals("null")) {
            bearing.setRollingElementsNumber(Integer.valueOf(bearingFeaturesList.get(9)));
        }

        if (!bearingFeaturesList.get(10).equals("null")) {
            bearing.setWeight(Integer.valueOf(bearingFeaturesList.get(11)));
        }

        if (!bearingFeaturesList.get(11).equals("null")) {
            bearing.setDynamic(Integer.valueOf(bearingFeaturesList.get(11)));
        }

        return bearing;
    }

}





























