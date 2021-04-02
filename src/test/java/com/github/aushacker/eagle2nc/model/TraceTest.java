/*
 * Copyright 2021 Stephen Davies
 *
 * This file is part of eagle2nc.
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
 * along with eagle2nc. If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.aushacker.eagle2nc.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import com.github.aushacker.eagle2nc.xml.XWire;

/**
 * Unit test for the Trace type.
 *
 * @author Stephen Davies
 * @since April 2021
 */
public class TraceTest implements TestConstants {

    /**
     * Start with a wire parallel to the X axis.
     * <p>
     * 10mm long, 1mm wide, starting at the origin and projecting along the +ve X axis.
     */
    @Test
    public void testHorizontal() {
        XWire w = new XWire(0, 0, 10, 0);
        w.setLayer("1"); // Top
        w.setWidth(1);   // 1mm
        
        Trace trace = new Trace(w);
        
        assertSame("Incorrect layer", Layer.TOP, trace.getLayer());
        
        Rectangle2D bounds = trace.getShape().getBounds2D();
        assertEquals("Incorrect X min", -0.5, bounds.getMinX(), DELTA);
        assertEquals("Incorrect X max", 10.5, bounds.getMaxX(), DELTA);
        assertEquals("Incorrect Y min", -0.5, bounds.getMinY(), DELTA);
        assertEquals("Incorrect Y max", 0.5, bounds.getMaxY(), DELTA);
    }
}
