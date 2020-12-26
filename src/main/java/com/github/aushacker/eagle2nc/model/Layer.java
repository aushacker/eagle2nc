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

package com.github.aushacker.eagle2nc.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Enumerates the various layers used in Eagle boards. Taken 
 * from the layer attribute of LayeredElement.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public enum Layer {
    TOP("1"),
    ROUTE_2("Route2"),
    ROUTE_3("Route3"),
    ROUTE_4("Route4"),
    ROUTE_5("Route5"),
    ROUTE_6("Route6"),
    ROUTE_7("Route7"),
    ROUTE_8("Route8"),
    ROUTE_9("Route9"),
    ROUTE_10("Route10"),
    ROUTE_11("Route11"),
    ROUTE_12("Route12"),
    ROUTE_13("Route13"),
    ROUTE_14("Route14"),
    ROUTE_15("Route15"),
    BOTTOM("16"),
    PADS("17"),
    VIAS("18"),
    UNROUTED("19"),
    DIMENSION("20"),
    T_PLACE("21"),
    B_PLACE("22"),
    T_ORIGINS("23"),
    B_ORIGINS("24"),
    T_NAMES("25"),
    B_NAMES("26"),
    T_VALUES("27"),
    B_VALUES("28"),
    T_STOP("29"),
    B_STOP("30"),
    T_CREAM("31"),
    B_CREAM("32"),
    DRILLS("44"),
    HOLES("45"),
    MILLING("46"),
    T_DOCU("51"),
    B_DOCU("52"),
    UNDEFINED("XX");

    /**
     * Map id to its Layer to allow fast lookup.
     */
    private static final Map<String,Layer> table;

    static {
        Map<String,Layer> temp = new HashMap<>();
        for (Layer l : Layer.values()) {
            temp.put(l.getId(), l);
        }
        table = Collections.unmodifiableMap(temp);
    }

    public static Layer lookup(String id) {
        Layer l = table.get(id);
        return (l == null) ? UNDEFINED : l;
    }

    /**
     * Layer number as encoded in the Eagle board file.
     */
    private String id;

    private Layer(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
