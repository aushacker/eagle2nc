/*
 * Copyright 2019 Stephen Davies
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

import java.util.HashMap;
import java.util.Map;

import com.github.aushacker.eagle2nc.xml.XLibrary;
import com.github.aushacker.eagle2nc.xml.XPackage;

/**
 * Wrapper for the XML Library type.
 *
 * @author Stephen Davies
 * @since March 2019
 */
public class Library {

    /**
     * Wrapped XML Library.
     */
    private XLibrary xLibrary;

    /**
     * Packages declared within the Library.
     * <p>
     * Key is package name e.g. DIL08.
     */
    private Map<String,Package> packages;

    public Library(XLibrary xLibrary) {
        this.xLibrary = xLibrary;

        packages = new HashMap<>();

        for (XPackage pkg : xLibrary.getPackages()) {
            Package p = new Package(pkg, this);
            packages.put(p.getName(), p);
        }
    }

    public String getName() {
        return xLibrary.getName();
    }

    public Map<String,Package> getPackages() {
        return packages;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Library(\"");
        sb.append(getName());
        sb.append("\"");

        return sb.toString();
    }
}
