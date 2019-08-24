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
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

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
			double theta = Math.atan2(getY2() - getY1(), getX2() - getX1());
			double length = Math.sqrt(Math.pow((getX2() - getX1()), 2) + Math.pow((getY2() - getY1()), 2));

			double t2 = theta + Math.PI / 2;

			Point2D.Double p1 = new Point2D.Double(
					getX1() + (getWidth() / 2 * Math.cos(theta + Math.PI / 2)),
					getY1() + (getWidth() / 2 * Math.sin(theta + Math.PI / 2)));	
			Point2D.Double p2 = new Point2D.Double(
					getX2() + (getWidth() / 2 * Math.cos(theta + Math.PI / 2)),
					getY2() + (getWidth() / 2 * Math.sin(theta + Math.PI / 2)));		
			Point2D.Double p3 = new Point2D.Double(
					getX2() + (getWidth() / 2 * Math.cos(theta - Math.PI / 2)),
					getY2() + (getWidth() / 2 * Math.sin(theta - Math.PI / 2)));
			Point2D.Double p4 = new Point2D.Double(
					getX1() + (getWidth() / 2 * Math.cos(theta - Math.PI / 2)),
					getY1() + (getWidth() / 2 * Math.sin(theta - Math.PI / 2)));

			Path2D.Double path = new Path2D.Double();
			path.moveTo(p1.x, p1.y);
			path.lineTo(p2.x,  p2.y);
			path.lineTo(p3.x,  p3.y);
			path.lineTo(p4.x,  p4.y);
			path.lineTo(p1.x,  p1.y);
			
			shape = path;
		}

		return shape;
	}

	public double getWidth() {
		return wire.getWidth();
	}

	public double getX1() {
		return wire.getX1();
	}
	
	public double getX2() {
		return wire.getX2();
	}
	
	public double getY1() {
		return wire.getY1();
	}
	
	public double getY2() {
		return wire.getY2();
	}
}
