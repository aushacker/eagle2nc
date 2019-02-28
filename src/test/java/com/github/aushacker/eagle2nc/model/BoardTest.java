/*
 * Copyright 2018 Stephen Davies
 *
 * This file is part of Eagle2nc.
 *
 * Eagle2nc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Eagle2nc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Eagle2nc. If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.aushacker.eagle2nc.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.aushacker.eagle2nc.xml.Eagle;

/**
 * Integration test for all of the model package.
 * Attempts to load the entire Arduino Mega board example.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class BoardTest {

	private static final String TEST_FILE = "data/Arduino_MEGA2560_ref.brd";

	private static final double THRESHOLD = 0.0000001;

	private static Eagle eagle;

	private Board board;
	
	@BeforeClass
	public static void setupClass() throws Exception {
		// Allow loading of eagle.dtd from file
		System.setProperty("javax.xml.accessExternalDTD", "all");
		JAXBContext jc = JAXBContext.newInstance(Eagle.class);
	    Unmarshaller u = jc.createUnmarshaller();
	    eagle = (Eagle) u.unmarshal(new File(TEST_FILE));
	}

	@Before
	public void setUp() {
		board = new Board(eagle);
	}

	@AfterClass
	public static void afterClass() {
		eagle = null;
	}

	@Test
	public void testSomething() {}
}
