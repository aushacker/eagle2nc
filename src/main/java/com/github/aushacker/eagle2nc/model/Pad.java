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

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import com.github.aushacker.eagle2nc.xml.XPad;

/**
 * 
 * @author Stephen Davies
 * @since April 2021
 */
public class Pad implements DrillHole {

    private XPad pad;

    private Area shape;

    public Pad(XPad pad) {
        this.pad = pad;
    }

    @Override
    public double getDrill() {
        // TODO Auto-generated method stub
        return 0;
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

    @Override
    public double getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY() {
        // TODO Auto-generated method stub
        return 0;
    }

}
