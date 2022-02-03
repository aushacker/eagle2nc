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

import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.JAXBException;

import com.github.aushacker.eagle2nc.xml.Parser;
import com.github.aushacker.eagle2nc.xml.XEagle;
import com.github.aushacker.eagle2nc.xml.XWire;

/**
 * Top level model type. Wraps XML data to provide higher
 * level information.
 * 
 * @author Stephen Davies
 * @since October 2018
 */
public class Board {

    private XEagle xmlModel;

    private Dimensions dimensions;

    private Collection<DrillHole> holes;

    private Map<String, Library> libraries;

    private Collection<Pad> pads;

    private Collection<Signal> signals;

    public Board(File f) throws ParseException {
        try {
            this.xmlModel = Parser.parse(f);
        }
        catch (JAXBException e) {
            throw new ParseException(e);
        }
    }

    public Board(XEagle xmlModel) {
        this.xmlModel = xmlModel;
    }

    public Dimensions getDimensions() {
        if (dimensions == null) {
            initializeDimensions();
        }
        return dimensions;
    }

    public Collection<DrillHole> getHoles() {
        if (holes == null) {
            holes = new ArrayList<>();

            // Get top-level holes
            getXmlModel().getHoles()
                .forEach(xHole -> holes.add(new Hole(xHole)));

            // Get vias
            getXmlModel().getVias()
                .forEach(xVia -> holes.add(new Via(xVia)));

            // Get pads
            // TODO
        }
        return holes;
    }

    public Collection<Library> getLibraries() {
        if (libraries == null) {
            libraries = new HashMap<>();

            xmlModel.getLibraries()
                .forEach(xLib -> libraries.put(xLib.getName(), new Library(xLib)));
        }

        return libraries.values();
    }

    public Collection<Pad> getPads() {
        if (pads == null) {
            pads = new ArrayList<>();

            // TODO
//            xmlModel.getLibraries()
//                .forEach(xLib -> pads.add(xLib.getName(), new Library(xLib)));
        }

        return pads;
    }

    public Collection<Signal> getSignals() {
        if (signals == null) {
            signals = new ArrayList<>();
            xmlModel.getSignals().forEach(xSignal -> signals.add(new Signal(xSignal)));
        }

        return signals;
    }

    public Collection<Via> getVias() {
        LinkedList<Via> result = new LinkedList<>();

        getSignals().forEach(s -> s.getVias().forEach(result::add));

        return result;
    }

    public XEagle getXmlModel() {
        return xmlModel;
    }

    private void initializeDimensions() {
        dimensions = new Dimensions();
        List<XWire> wires = new LinkedList<>(xmlModel.getDimensionWires());

        // pluck first wire
        XWire first = wires.get(0);
        XWire previous = first;
        wires.remove(0);
        dimensions.add(new Point2D.Double(first.getX1(), first.getY1()));
        Point2D.Double next = new Point2D.Double(first.getX2(), first.getY2());
        dimensions.add(next);

        while (!wires.isEmpty()) {
            // Search for next connected wire
            XWire current = null;
            for (XWire w : wires) {
                if (w.isConnected(previous)) {
                    current = w;
                    break;
                }
            }

            // Not found (should not really happen)
            if (current == null) {
                throw new IllegalStateException("Something weird with the board dimensions.");
            }

            // Handle case where wire directions are reversed
            if ((next.getX() == current.getX1()) && (next.getY() == current.getY1())) {
                // head to tail
                next = new Point2D.Double(current.getX2(), current.getY2());
            } else {
                // head/tails are reversed
                next = new Point2D.Double(current.getX1(), current.getY1());
            }

            dimensions.add(next);
            wires.remove(current);
            previous = current;
        }

        // Back to start
        //dimensions.add(new Point2D.Double(first.getX1(), first.getY1()));
    }
}
