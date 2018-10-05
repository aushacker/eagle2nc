package com.github.aushacker.eagle2nc.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

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
	
	@Test
	public void testEagleElement() {
		assertEquals("6.2", eagle.getVersion());
		assertNotNull(eagle.getDrawing());
	}
}
