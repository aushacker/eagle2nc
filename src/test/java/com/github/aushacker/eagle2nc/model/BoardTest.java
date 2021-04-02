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

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.aushacker.eagle2nc.xml.Parser;
import com.github.aushacker.eagle2nc.xml.XEagle;

/**
 * Integration test for all of the model package.
 * Attempts to load the entire Arduino Mega board example.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class BoardTest {

    private static final String TEST_FILE = "data/Arduino_MEGA2560_ref.brd";
    //private static final String TEST_FILE = "data/ms-adsr-02.brd";

    //private static final double THRESHOLD = 0.0000001;

    private static XEagle eagle;

    private Board board;
    
    @BeforeClass
    public static void setupClass() throws Exception {
        eagle = Parser.parse(new File(TEST_FILE));
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
    public void testDimensions() {
        assertEquals(10, board.getDimensions().size());
    }

    @Test
    public void testHoleCount() {
        board.getHoles()
            .forEach(h -> System.out.println(h));
        assertEquals(150, board.getHoles().size());
    }

    @Test
    public void testLibraryCount() {
        assertEquals(17, board.getLibraries().size());
    }

    @Test
    public void testPackageCount() {
        assertEquals("pinhead", 5, board.getLibrary("pinhead").getPackages().size());
        assertEquals("rcl", 3, board.getLibrary("rcl").getPackages().size());
        assertEquals("diode", 1, board.getLibrary("diode").getPackages().size());
        assertEquals("led", 1, board.getLibrary("led").getPackages().size());
        assertEquals("linear", 3, board.getLibrary("linear").getPackages().size());
        assertEquals("powersupply", 1, board.getLibrary("powersupply").getPackages().size());
    }

    @Test
    public void testElementCount() {
        assertEquals(72, board.getElements().size());
    }

    @Test
    public void testPadCount() {
        assertEquals(400, board.getPads().size());
    }
}
