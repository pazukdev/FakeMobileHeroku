package com.pazukdev.test.newfeatures;

import com.pazukdev.entities.objectanalyzer.version1.ObjectAnalyzer1;

public class TestSuperClass {

    private  String superPrivateField = "Super Private Field";
    protected String superProtectedField = "Super Protected field";
    public String superPublicField = "Super Public Field";

    @Override
    public String toString() {
        return new ObjectAnalyzer1(this).getStateReport(true);
    }





}





















