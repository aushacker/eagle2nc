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

import jakarta.xml.bind.annotation.XmlAttribute;

/**
 * @author Stephen Davies
 * @since October 2018
 */
public class XSmd extends XLayeredElement {

    // ID
    @XmlAttribute
    private String name;

    @XmlAttribute
    private double dx;
    
    @XmlAttribute
    private double dy;

    @XmlAttribute
    private double x;
    
    @XmlAttribute
    private double y;

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
