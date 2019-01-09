package com.pazukdev.services;

import com.pazukdev.dao.DAOBearing;
import com.pazukdev.entities.OldBearing;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OldBearingService {

	private static OldBearingService instance;
	private static final Logger LOGGER = Logger.getLogger(OldBearingService.class.getName());

	private final HashMap<Long, OldBearing> bearings = new HashMap<>();
	private DAOBearing daoBearing = new DAOBearing();


	private OldBearingService() {}


	public static OldBearingService getInstance() {
		if (instance == null) {
			instance = new OldBearingService();
			instance.ensureTestData();
		}
		return instance;
	}


	public synchronized List<OldBearing> findAll() {
		return daoBearing.getList();
	}


	public synchronized Integer count() {
		return bearings.size();
	}


	public synchronized void delete(OldBearing oldBearing) {
        daoBearing.delete(oldBearing);
	}


	public synchronized void save(OldBearing oldBearing) {
		if (oldBearing == null) {
			LOGGER.log(Level.SEVERE, "Bearing is null.");
			return;
		}

		if (oldBearing.getId() != null) {
			daoBearing.update(oldBearing);
			return;
		}

		daoBearing.create(oldBearing);
	}


	public void ensureTestData() {

		if (findAll().isEmpty()) {
			final String[] bearingsData = new String[] {
					"207;ball;Engine;2",
					"205;ball;Engine;1",
					"364708 DM;roller;Engine;2",
					"7201234-A;plain;Engine;2",
					"7201107;plain;Engine;1",
					"778707;ball;Frame and wheels;2",
					"7204;roller;Frame and wheels;8",
					"201;ball;Generator;1",
					"200;ball;Generator;1",
					"304;ball;Gearbox;2",
					"12204;roller;Gearbox;1",
					"948066;ball;Gearbox;1",
					"120;roller;Zzzfoobar;1",
					"121;roller;Zzzfoobar;1",
					"122;roller;Zzzfoobar;1",
					"123;roller;Zzzfoobar;1",
					"124;roller;Zzzfoobar;1",
					"125;roller;Zzzfoobar;1",
					"126;roller;Zzzfoobar;1",
					"127;roller;Zzzfoobar;1",
					"128;roller;Zzzfoobar;1",
					"129;roller;Zzzfoobar;1",
					"130;roller;Zzzfoobar;1",
					"131;roller;Zzzfoobar;1",
					"132;roller;Zzzfoobar;1",
					"133;roller;Zzzfoobar;1",
					"134;roller;Zzzfoobar;1",
					"135;roller;Zzzfoobar;1",
					"136;roller;Zzzfoobar;1",
					"137;roller;Zzzfoobar;1",
					"138;roller;Zzzfoobar;1",
					"140;roller;Zzzfoobar;1"
			};

			for (String bearingSourceString : bearingsData) {
				String[] splittedString = bearingSourceString.split(";");
				OldBearing oldBearing = new OldBearing();
				oldBearing.setNumberOfOriginal(splittedString[0]);
				oldBearing.setType(splittedString[1]);
				oldBearing.setMajorLocation(splittedString[2]);
				oldBearing.setQuantity(Integer.valueOf(splittedString[3]));
				save(oldBearing);
			}
		}
	}

}
