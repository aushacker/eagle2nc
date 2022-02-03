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
 * In Eagle terms XElements represent components mounted on the PCB.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class XElement {

    @XmlAttribute
    private String library;

    @XmlAttribute
    private String name;

    // package is Java reserved word
    @XmlAttribute(name="package")
    private String pkg;

    @XmlAttribute(name="rot")
    private String rotation;
    
    @XmlAttribute
    private String smashed;

    @XmlAttribute
    private String value;
    
    @XmlAttribute
    private double x;

    @XmlAttribute
    private double y;

    public String getLibrary() {
        return library;
    }

    public String getName() {
        return name;
    }

    public String getPkg() {
        return pkg;
    }

    public String getRotation() {
        return rotation;
    }

    public String getSmashed() {
        return smashed;
    }

    public String getValue() {
        return value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "XElement(" + name + ")";
    }
}
