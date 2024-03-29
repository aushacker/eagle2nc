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

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * @author Stephen Davies
 * @since October 2018
 */
@XmlEnum
public enum XViaShape {

    @XmlEnumValue("octagon")
    OCTAGON("octagon"),
    @XmlEnumValue("round")
    ROUND("round"),
    @XmlEnumValue("square")
    SQUARE("square");

    private final String value;

    private XViaShape(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
