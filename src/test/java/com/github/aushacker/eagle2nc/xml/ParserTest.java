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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.aushacker.eagle2nc.xml.XEagle;

/**
 * Integration test for all of the xml package.
 * Attempts to load the entire Arduino Mega board example.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class ParserTest {

	private static final String TEST_FILE = "data/Arduino_MEGA2560_ref.brd";

	// Comparison threshold for JUnit assertEquals (double version)
	private static final double DELTA = 0.0000001;

	private static XEagle eagle;

	@BeforeClass
	public static void setupClass() throws Exception {
		//DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//dbf.setFeature("http://xml.org/sax/features/validation", false);
		//dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		//dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	    eagle = Parser.parse(new File(TEST_FILE));
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
		assertEquals(9, eagle.getDimensionWires().size());
		assertEquals(6, eagle.getHoles().size());
		assertEquals(144, eagle.getVias().size());
		assertEquals(17, eagle.getLibraries().size());
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
		List<XLayer> layers = eagle.getDrawing().getLayers();
		assertEquals(60, layers.size());
	}

	/**
	 * Check that the first layer deserializes correctly. 
	 */
	@Test
	public void testLayerElement() {
		XLayer layer = eagle.getDrawing().getLayers().get(0);
		
		assertEquals("1", layer.getNumber());
		assertEquals("Top", layer.getName());
		assertEquals("4", layer.getColor());
		assertEquals("1", layer.getFill());
		assertEquals("yes", layer.getActive());
		assertEquals("yes", layer.getVisible());
	}

	@Test
	public void testBoardElement() {
		XBoard board = eagle.getDrawing().getBoard();
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
		XBoard board = eagle.getDrawing().getBoard();
		XWire wire = (XWire) board.getPlain().get(0);
	
		assertEquals("20", wire.getLayer());
		assertEquals(0, wire.getX1(), DELTA);
		assertEquals(53.34, wire.getY1(), DELTA);
		assertEquals(96.52, wire.getX2(), DELTA);
		assertEquals(53.34, wire.getY2(), DELTA);
		assertEquals(0.254, wire.getWidth(), DELTA);
	}

	/**
	 * First circle element occurs in the boards &lt;plain&gt; section.
	 */
	@Test
	public void testCircleElement() {
		XBoard board = eagle.getDrawing().getBoard();
		XCircle circle = (XCircle) board.getPlain().get(45);
		
		assertEquals("21", circle.getLayer());
		assertEquals(22.225, circle.getX(), DELTA);
		assertEquals(48.514, circle.getY(), DELTA);
		assertEquals(0.1796, circle.getRadius(), DELTA);
		assertEquals(0.3048, circle.getWidth(), DELTA);
	}

	/**
	 * First rectangle element occurs in the boards &lt;plain&gt; section.
	 */
	@Test
	public void testRectangleElement() {
		XBoard board = eagle.getDrawing().getBoard();
		XRectangle rect = (XRectangle) board.getPlain().get(46);
		
		assertEquals("1", rect.getLayer());
		assertEquals(1, rect.getX1(), DELTA);
		assertEquals(47, rect.getY1(), DELTA);
		assertEquals(13, rect.getX2(), DELTA);
		assertEquals(52, rect.getY2(), DELTA);
	}

	/**
	 * First hole element occurs in the boards &lt;plain&gt; section.
	 */
	@Test
	public void testHoleElement() {
		XBoard board = eagle.getDrawing().getBoard();
		XHole hole = (XHole) board.getPlain().get(47);
		
		assertEquals(96.52, hole.getX(), DELTA);
		assertEquals(2.54, hole.getY(), DELTA);
		assertEquals(3.2, hole.getDrill(), DELTA);
	}

	/**
	 * Checking component libraries included in the design.
	 */
	@Test
	public void testLibraryElement() {
		List<XLibrary> libraries = eagle.getDrawing().getBoard().getLibraries();
		XLibrary lib = libraries.get(0);
		
		assertEquals("pinhead", lib.getName());
		assertEquals(5, lib.getPackages().size());
	}

	/**
	 * Package element within previous Library.
	 */
	@Test
	public void testPackageElement() {
		List<XLibrary> libraries = eagle.getDrawing().getBoard().getLibraries();
		XPackage pkg = libraries.get(0).getPackages().get(0);
		
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
		List<XLibrary> libraries = eagle.getDrawing().getBoard().getLibraries();
		XPackage pkg = libraries.get(0).getPackages().get(1); // package 1X08
		XPad pad = (XPad) pkg.getElements().get(57);
		
		assertEquals("1", pad.getName());
		assertEquals(-8.89, pad.getX(), DELTA);
		assertEquals(0, pad.getY(), DELTA);
		assertEquals(1.016, pad.getDrill(), DELTA);
		assertEquals(XPadShape.LONG, pad.getShape());
		assertEquals("R90", pad.getRotation());
	}

	/**
	 * Library rcl contains the first smd element.
	 */
	@Test
	public void testSmdElement() {
		List<XLibrary> libraries = eagle.getDrawing().getBoard().getLibraries();
		XPackage pkg = libraries.get(1).getPackages().get(0); // package PANASONIC_D
		XSmd smd = (XSmd) pkg.getElements().get(18);

		assertEquals("-", smd.getName());
		assertEquals("1", smd.getLayer());
		assertEquals(-2.4, smd.getX(), DELTA);
		assertEquals(0, smd.getY(), DELTA);
		assertEquals(3, smd.getDx(), DELTA);
		assertEquals(1.4, smd.getDy(), DELTA);
	}

	/**
	 * Library rcl contains the first polygon element.
	 */
	@Test
	public void testPolygonElement() {
		List<XLibrary> libraries = eagle.getDrawing().getBoard().getLibraries();
		XPackage pkg = libraries.get(1).getPackages().get(0); // package PANASONIC_D
		XPolygon poly = (XPolygon) pkg.getElements().get(22);

		assertEquals("51", poly.getLayer());
		assertEquals(0.1016, poly.getWidth(), DELTA);
		assertEquals(8, poly.getVertices().size());
	}

	/**
	 * Library rcl contains the first vertex element.
	 */
	@Test
	public void testVertexElement() {
		List<XLibrary> libraries = eagle.getDrawing().getBoard().getLibraries();
		XPackage pkg = libraries.get(1).getPackages().get(0); // package PANASONIC_D
		XVertex v = ((XPolygon) pkg.getElements().get(22)).getVertices().get(0);

		assertEquals(-2.15, v.getX(), DELTA);
		assertEquals(2.15, v.getY(), DELTA);
	}

	@Test
	public void testElementElement() {
		List<XElement> elements = eagle.getDrawing().getBoard().getElements();
		XElement e = elements.get(0);

		assertEquals("ICSP", e.getName());
		assertEquals("pinhead", e.getLibrary());
		assertEquals("2X03", e.getPkg());
		assertEquals("ICSP", e.getValue());
		assertEquals(64.897, e.getX(), DELTA);
		assertEquals(27.94, e.getY(), DELTA);
		assertEquals("yes", e.getSmashed());
		assertEquals("R270", e.getRotation());
	}

	@Test
	public void testSignalElement() {
		List<XSignal> signals = eagle.getDrawing().getBoard().getSignals();
		XSignal s = signals.get(0);

		assertEquals("+5V", s.getName());
		assertEquals(32, s.getContactReferences().size());
		assertEquals(163, s.getWires().size());
		assertEquals(24, s.getVias().size());
	}

	@Test
	public void testContactRefElement() {
		List<XSignal> signals = eagle.getDrawing().getBoard().getSignals();
		XContactRef ref = signals.get(0).getContactReferences().get(0);

		assertEquals("ICSP", ref.getElement());
		assertEquals("2", ref.getPad());
	}

	@Test
	public void testViaElement() {
		List<XSignal> signals = eagle.getDrawing().getBoard().getSignals();
		XVia via = signals.get(0).getVias().get(0);

		assertEquals(30.353, via.getX(), DELTA);
		assertEquals(4.953, via.getY(), DELTA);
		assertEquals("1-16", via.getExtent());
		assertEquals(0.4064, via.getDrill(), DELTA);
		assertEquals(XViaShape.OCTAGON, via.getShape());
	}
}
