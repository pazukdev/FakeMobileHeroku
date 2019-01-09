package com.pazukdev.entities.objectanalyzer.version2;

import com.pazukdev.entities.objectanalyzer.version2.OutputFilter;

/**
 * @author Siarhei Sviarkaltsau
 */
public interface ObjectAnalyzer {

    String getStateReport(final OutputFilter filter);

    String getStateReport();

}
