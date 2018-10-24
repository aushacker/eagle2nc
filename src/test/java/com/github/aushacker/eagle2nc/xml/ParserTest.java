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
		assertEquals(60, layers.size());
	}

	/**
	 * Check that the first layer deserialises correctly. 
	 */
	@Test
	public void testLayerElement() {
		Layer layer = eagle.getDrawing().getLayers().get(0);
		
		assertEquals("1", layer.getNumber());
		assertEquals("Top", layer.getName());
		assertEquals("4", layer.getColor());
		assertEquals("1", layer.getFill());
		assertEquals("yes", layer.getActive());
		assertEquals("yes", layer.getVisible());
	}

	@Test
	public void testBoardElement() {
		Board board = eagle.getDrawing().getBoard();
		assertNotNull(board);
		assertEquals(53, board.getPlain().size());
		assertEquals(17, board.getLibraries().size());
		assertEquals(72, board.getElements().size());
		assertEquals(108, board.getSignals().size());
	}

	/**
	 * First wire element occurs in the boards &lt;plain&gt; section.
	 * It represents the boards dimension layer.
	 */
	@Test
	public void testWireElement() {
		Board board = eagle.getDrawing().getBoard();
		Wire wire = (Wire) board.getPlain().get(0);
	
		assertEquals("20", wire.getLayer());
		assertEquals(0, wire.getX1(), THRESHOLD);
		assertEquals(53.34, wire.getY1(), THRESHOLD);
		assertEquals(96.52, wire.getX2(), THRESHOLD);
		assertEquals(53.34, wire.getY2(), THRESHOLD);
		assertEquals(0.254, wire.getWidth(), THRESHOLD);
	}

	/**
	 * First circle element occurs in the boards &lt;plain&gt; section.
	 */
	@Test
	public void testCircleElement() {
		Board board = eagle.getDrawing().getBoard();
		Circle circle = (Circle) board.getPlain().get(45);
		
		assertEquals("21", circle.getLayer());
		assertEquals(22.225, circle.getX(), THRESHOLD);
		assertEquals(48.514, circle.getY(), THRESHOLD);
		assertEquals(0.1796, circle.getRadius(), THRESHOLD);
		assertEquals(0.3048, circle.getWidth(), THRESHOLD);
	}

	/**
	 * First rectangle element occurs in the boards &lt;plain&gt; section.
	 */
	@Test
	public void testRectangleElement() {
		Board board = eagle.getDrawing().getBoard();
		Rectangle rect = (Rectangle) board.getPlain().get(46);
		
		assertEquals("1", rect.getLayer());
		assertEquals(1, rect.getX1(), THRESHOLD);
		assertEquals(47, rect.getY1(), THRESHOLD);
		assertEquals(13, rect.getX2(), THRESHOLD);
		assertEquals(52, rect.getY2(), THRESHOLD);
	}

	/**
	 * First hole element occurs in the boards &lt;plain&gt; section.
	 */
	@Test
	public void testHoleElement() {
		Board board = eagle.getDrawing().getBoard();
		Hole hole = (Hole) board.getPlain().get(47);
		
		assertEquals(96.52, hole.getX(), THRESHOLD);
		assertEquals(2.54, hole.getY(), THRESHOLD);
		assertEquals(3.2, hole.getDrill(), THRESHOLD);
	}

	/**
	 * Checking component libraries included in the design.
	 */
	@Test
	public void testLibraryElement() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Library lib = libraries.get(0);
		
		assertEquals("pinhead", lib.getName());
		assertEquals(5, lib.getPackages().size());
	}

	/**
	 * Package element within previous Library.
	 */
	@Test
	public void testPackageElement() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Package pkg = libraries.get(0).getPackages().get(0);
		
		assertEquals("2X03", pkg.getName());
		assertEquals("<b>PIN HEADER</b>", pkg.getDescription());
		assertEquals(34, pkg.getElements().size());
	}

	/**
	 * Using a pad element in the second package because it also has the rotation
	 * attribute.
	 */
	@Test
	public void testPadElement() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Package pkg = libraries.get(0).getPackages().get(1); // package 1X08
		Pad pad = (Pad) pkg.getElements().get(57);
		
		assertEquals("1", pad.getName());
		assertEquals(-8.89, pad.getX(), THRESHOLD);
		assertEquals(0, pad.getY(), THRESHOLD);
		assertEquals(1.016, pad.getDrill(), THRESHOLD);
		assertEquals(PadShape.LONG, pad.getShape());
		assertEquals("R90", pad.getRotation());
	}

	/**
	 * Library rcl contains the first smd element.
	 */
	@Test
	public void testSmdElement() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Package pkg = libraries.get(1).getPackages().get(0); // package PANASONIC_D
		Smd smd = (Smd) pkg.getElements().get(18);

		assertEquals("-", smd.getName());
		assertEquals("1", smd.getLayer());
		assertEquals(-2.4, smd.getX(), THRESHOLD);
		assertEquals(0, smd.getY(), THRESHOLD);
		assertEquals(3, smd.getDx(), THRESHOLD);
		assertEquals(1.4, smd.getDy(), THRESHOLD);
	}

	/**
	 * Library rcl contains the first polygon element.
	 */
	@Test
	public void testPolygonElement() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Package pkg = libraries.get(1).getPackages().get(0); // package PANASONIC_D
		Polygon poly = (Polygon) pkg.getElements().get(22);

		assertEquals("51", poly.getLayer());
		assertEquals(0.1016, poly.getWidth(), THRESHOLD);
		assertEquals(8, poly.getVertices().size());
	}

	/**
	 * Library rcl contains the first vertex element.
	 */
	@Test
	public void testVirtexElement() {
		List<Library> libraries = eagle.getDrawing().getBoard().getLibraries();
		Package pkg = libraries.get(1).getPackages().get(0); // package PANASONIC_D
		Vertex v = ((Polygon) pkg.getElements().get(22)).getVertices().get(0);

		assertEquals(-2.15, v.getX(), THRESHOLD);
		assertEquals(2.15, v.getY(), THRESHOLD);
	}

	@Test
	public void testElementElement() {
		List<Element> elements = eagle.getDrawing().getBoard().getElements();
		Element e = elements.get(0);

		assertEquals("ICSP", e.getName());
		assertEquals("pinhead", e.getLibrary());
		assertEquals("2X03", e.getPkg());
		assertEquals("ICSP", e.getValue());
		assertEquals(64.897, e.getX(), THRESHOLD);
		assertEquals(27.94, e.getY(), THRESHOLD);
		assertEquals("yes", e.getSmashed());
		assertEquals("R270", e.getRotation());
	}
}
