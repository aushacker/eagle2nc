/*
 * Copyright 2019 Stephen Davies
 *
 * This file is part of eagle2nc.
 *
 * eagle2nc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * eagle2nc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with eagle2nc. If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.aushacker.eagle2nc.model;

import java.awt.Shape;
import java.awt.geom.Line2D;

import com.github.aushacker.eagle2nc.xml.XWire;

/**
 * @author Stephen Davies
 * @since 2019
 */
public class Trace {

	private Layer layer;

	private Shape shape;

	private XWire wire;
	
	public Trace(XWire wire) {
		this.wire = wire;
	}

	public Layer getLayer() {
		if (layer == null) {
			layer = Layer.lookup(wire.getLayer());
		}
		
		return layer;
	}

	public Shape getShape() {
		if (shape == null) {
			shape = new Line2D.Double(wire.getX1(), wire.getY1(), wire.getX2(), wire.getY2());
		}

		return shape;
	}
}
