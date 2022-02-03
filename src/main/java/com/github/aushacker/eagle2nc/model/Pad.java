/*
 * Copyright 2022 Stephen Davies
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

import com.github.aushacker.eagle2nc.xml.XPad;

/**
 * Adapts an XPad for drilling. Currently only plated through hole (PTH)
 * pads are supported.
 * <p>
 * Holes are located using absolute coordinate values.
 * 
 * @author Stephen Davies
 * @since February 2022
 */
public class Pad implements DrillHole {

    private Area shape;

    private XPad pad;

    public Pad(XPad pad) {
        this.pad = pad;
    }

    public double getDrill() {
        return pad.getDrill();
    }

    public Ellipse2D.Double getHole() {
        return DrillHole.holeShape(getX(), getY(), getDrill());
    }

    public Shape getShape() {
        if (shape == null) {
            double diameter = getDrill() * 2; // TODO fudge
            Ellipse2D.Double outer = new Ellipse2D.Double(getX() - (diameter/2),
                        getY() - (diameter/2),
                        diameter,
                        diameter);

            shape = new Area(outer);
            //shape.subtract(new Area(getHole()));
        }

        return shape;
    }

    public double getX() {
        return pad.getX();
    }

    public double getY() {
        return pad.getY();
    }

    @Override
    public String toString() {
        return "Pad(" + getX() + ", " + getY() + ", " + getDrill() + ")";
    }
}
