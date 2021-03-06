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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test XWire, not that there is much to actually test.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class XWireTest {

    /**
     * Object under test.
     */
    private XWire wire;

    @Before
    public void setUp() {
        wire = new XWire(0, 0, 10, 10);
    }

    @Test
    public void testIsConnected() {
        XWire w2 = new XWire(10, 10, 20, 20);
        XWire w3 = new XWire(20, 20, 10, 10);
        XWire w4 = new XWire(-5, -5, 20, 20);

        assertTrue(wire.isConnected(w2));
        assertTrue(wire.isConnected(w3));
        assertFalse(wire.isConnected(w4));
    }

    @Test
    public void testIsWire() {
        assertTrue(wire.isWire());
    }

    @Test
    public void testToString() {
        assertEquals("XWire( (0.0,0.0) to (10.0,10.0) )", wire.toString());
    }
}
