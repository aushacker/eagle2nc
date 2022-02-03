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

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;

/**
 * @author Stephen Davies
 * @since October 2018
 */
public class XSignal {

    @XmlAttribute
    private String name;

    @XmlElements({
        @XmlElement(name="contactref", type=XContactRef.class)
    })
    private List<XContactRef> contactReferences;

    @XmlElements({
        @XmlElement(name="wire", type=XWire.class)
    })
    private List<XWire> wires;

    @XmlElements({
        @XmlElement(name="via", type=XVia.class)
    })
    private List<XVia> vias;

    public List<XContactRef> getContactReferences() {
        return contactReferences;
    }

    public String getName() {
        return name;
    }

    public List<XVia> getVias() {
        if (vias == null) {
            vias = new ArrayList<>();
        }
        return vias;
    }

    public List<XWire> getWires() {
        if (wires == null) {
            wires = new ArrayList<>();
        }

        return wires;
    }
}
