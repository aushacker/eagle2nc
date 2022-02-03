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
 * Integration tests for the entire model package.
 * Attempts to load the entire Arduino Mega board example.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class BoardTest {

    private static final String TEST_FILE = "data/Arduino_MEGA2560_ref.brd";

    //private static final double THRESHOLD = 0.0000001;

    private Board board;
    
    @Before
    public void setUp() throws ParseException {
        board = new Board(new File(TEST_FILE));
    }

    @Test
    public void testDimensions() {
        Dimensions d = board.getDimensions();
        System.out.println(d);
    }

    @Test
    public void testHoleCount() {
        assertEquals(150, board.getHoles().size());
    }

    @Test
    public void testLibraryCount() {
        assertEquals(17, board.getLibraries().size());
    }
}
