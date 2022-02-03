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

import com.github.aushacker.eagle2nc.xml.XVia;

/**
 * Adapts an XVia for drilling. Currently only plated through hole (PTH)
 * vias are supported. Blind and buried vias are not possible. It's difficult
 * to produce that level of complexity using desktop methods.
 * <p>
 * Holes are located using absolute coordinate values.
 * 
 * @author Stephen Davies
 * @since March 2019
 */
public class Via implements DrillHole {

    private Area shape;

    private XVia xVia;

    public Via(XVia via) {
        this.xVia = via;
    }

    public double getDrill() {
        return xVia.getDrill();
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
        return xVia.getX();
    }

    public double getY() {
        return xVia.getY();
    }

    @Override
    public String toString() {
        return "Via(" + getX() + ", " + getY() + ", " + getDrill() + ")";
    }
}
