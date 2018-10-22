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

	private static final double THRESHOLD = 0.0000001;

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
	}

	/**
	 * Check that the first layer deserialises correctly. 
	 */
	@Test
	public void testLayer() {
		Layer layer = eagle.getDrawing().getLayers().get(0);
		
		assertEquals("1", layer.getNumber());
		assertEquals("Top", layer.getName());
		assertEquals("4", layer.getColor());
		assertEquals("1", layer.getFill());
		assertEquals("yes", layer.getActive());
		assertEquals("yes", layer.getVisible());
	}

	@Test
	public void testBoard() {
		Board board = eagle.getDrawing().getBoard();
		assertNotNull(board);
		assertEquals(53, board.getPlain().size());
	}

	/**
	 * Checking second library as this also includes a circle element.
	 */
	@Test
	public void testLibrary() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Library lib = libraries.get(1);
		
		assertEquals("rcl", lib.getName());
		assertEquals(3, lib.getPackages().size());
	}

	/**
	 * Package element within previous Library.
	 */
	@Test
	public void testPackage() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Package pkg = libraries.get(1).getPackages().get(1);
		
		assertEquals("C0603", pkg.getName());
		assertEquals("<b>CAPACITOR</b>", pkg.getDescription());
		assertEquals(11, pkg.getElements().size());
	}

	/**
	 * Plenty to choose from, using the boards second library element.
	 */
	@Test
	public void testCircle() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Library lib = libraries.get(1);
		Circle circle = (Circle) lib.getPackages().get(0).getElements().get(17);
		
		assertEquals("51", circle.getLayer());
		assertEquals(0, circle.getX(), THRESHOLD);
		assertEquals(0, circle.getY(), THRESHOLD);
		assertEquals(3.1, circle.getRadius(), THRESHOLD);
		assertEquals(0.1016, circle.getWidth(), THRESHOLD);
	}

	/**
	 * Plenty to choose from, using the boards second library element.
	 */
	@Test
	public void testRectangle() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Library lib = libraries.get(1);
		Rectangle rect = (Rectangle) lib.getPackages().get(1).getElements().get(8);
		
		assertEquals("51", rect.getLayer());
		assertEquals(-0.8382, rect.getX1(), THRESHOLD);
		assertEquals(-0.4699, rect.getY1(), THRESHOLD);
		assertEquals(-0.3381, rect.getX2(), THRESHOLD);
		assertEquals(0.4801, rect.getY2(), THRESHOLD);
	}

	/**
	 * First wire element occurs in the boards &lt;plain&gt; section.
	 * It represents the boards dimension layer.
	 */
	@Test
	public void testWire() {
		Board board = eagle.getDrawing().getBoard();
		Wire wire = (Wire) board.getPlain().get(0);

		assertEquals("20", wire.getLayer());
		assertEquals(0, wire.getX1(), THRESHOLD);
		assertEquals(53.34, wire.getY1(), THRESHOLD);
		assertEquals(96.52, wire.getX2(), THRESHOLD);
		assertEquals(53.34, wire.getY2(), THRESHOLD);
		assertEquals(0.254, wire.getWidth(), THRESHOLD);
	}
}
