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

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Stephen Davies
 * @since October 2018
 */
public class XWire extends XLayeredElement {

	// TODO add attr for curve (package CAT16, BOURNS)
	
	@XmlAttribute
	private double x1;
	
	@XmlAttribute
	private double y1;

	@XmlAttribute
	private double x2;
	
	@XmlAttribute
	private double y2;
	
	@XmlAttribute
	private double width;

	public double getWidth() {
		return width;
	}

	public double getX1() {
		return x1;
	}

	public double getX2() {
		return x2;
	}

	public double getY1() {
		return y1;
	}

	public double getY2() {
		return y2;
	}

	@Override
	public boolean isWire() {
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("XWire( (");
		result.append(x1);
		result.append(",");
		result.append(y1);
		result.append(") to (");
		result.append(x2);
		result.append(",");
		result.append(y2);
		result.append(") )");
		return result.toString();
	}
}