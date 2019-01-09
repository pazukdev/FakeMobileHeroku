package com.pazukdev.test.newfeatures;

import com.pazukdev.entities.bearing.Bearing;
import com.pazukdev.services.BearingService;

public class Main {



    public static void main(String[] args) throws IllegalAccessException {

        // TODO create repository on Git Hub for me!


        System.out.println(BearingService.getInstance().findBearing("207"));
        //System.out.println(new TestSubClass());





        //System.out.println(bearingService.findBearing("207").getReportString(true, true));



    }

}
