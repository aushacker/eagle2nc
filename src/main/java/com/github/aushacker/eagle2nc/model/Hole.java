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

import com.github.aushacker.eagle2nc.xml.XHole;

/**
 * A top level hole located within the &lt;plain&gt; element.
 * <p>
 * Holes are located using absolute coordinate values.
 * 
 * @author Stephen Davies
 * @since March 2019
 */
public class Hole implements DrillHole {

    private XHole xHole;

    public Hole(XHole xHole) {
        this.xHole = xHole;
    }

    public double getDrill() {
        return xHole.getDrill();
    }

    public double getX() {
        return xHole.getX();
    }

    public double getY() {
        return xHole.getY();
    }

    @Override
    public String toString() {
        return "Hole(" + getX() + ", " + getY() + ", " + getDrill() + ")";
    }
}
