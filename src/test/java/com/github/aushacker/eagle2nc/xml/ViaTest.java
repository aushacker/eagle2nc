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

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test Via, not that there is much to actually test.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class ViaTest {

	/**
	 * Object under test.
	 */
	private Via via;

	@Before
	public void setUp() {
		via = new Via();
	}

	@Test
	public void testGetShapeLazyInitialisation() {
		assertSame(ViaShape.ROUND, via.getShape());
	}

	@Test
	public void testGetShapeWhenExplicitlySet() {
		via.setShape(ViaShape.SQUARE);
		assertSame(ViaShape.SQUARE, via.getShape());
	}
}
