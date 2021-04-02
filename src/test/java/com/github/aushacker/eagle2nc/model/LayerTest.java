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

import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * Unit test for the Layer enum.
 *
 * @author Stephen Davies
 * @since April 2021
 */
public class LayerTest {

    @Test
    public void testLookupSuccess() {
        assertSame(Layer.TOP, Layer.lookup("1"));
    }

    @Test
    public void testLookupFailure() {
        assertSame(Layer.UNDEFINED, Layer.lookup(""));
    }
}
