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
package com.github.aushacker.eagle2nc.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.AfterClass;
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
public class ParserTest {

	private static final String TEST_FILE = "data/Arduino_MEGA2560_ref.brd";
	
	private static Eagle eagle;
	
	@BeforeClass
	public static void setupClass() throws Exception {
		// Allow loading of eagle.dtd from file
		System.setProperty("javax.xml.accessExternalDTD", "all");
		//DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//dbf.setFeature("http://xml.org/sax/features/validation", false);
		//dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		//dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		JAXBContext jc = JAXBContext.newInstance(Eagle.class);
	    Unmarshaller u = jc.createUnmarshaller();
	    eagle = (Eagle) u.unmarshal(new File(TEST_FILE));
	}
	
	@AfterClass
	public static void afterClass() {
		eagle = null;
	}

	/**
	 * Validate binding of the &lt;eagle&gt; element. 
	 */
	@Test
	public void testEagleElement() {
		assertEquals("6.2", eagle.getVersion());
	}

	/**
	 * Validate binding of the &lt;drawing&gt; element. 
	 */
	@Test
	public void testDrawingElement() {
		assertNotNull(eagle.getDrawing());
	}

	/**
	 * Check that the board layers have been loaded ok. 
	 */
	@Test
	public void testLayers() {
		List<Layer> layers = eagle.getDrawing().getLayers();
		assertEquals("Layer count invalid -", 60, layers.size());
		
		// layers[0] should be the top layer
		assertEquals("1", layers.get(0).getNumber());
		assertEquals("Top", layers.get(0).getName());
	}
	
	@Test
	public void testBoard() {
		Board board = eagle.getDrawing().getBoard();
		assertNotNull(board);
	}
}
