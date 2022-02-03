/*
 * Copyright 2022 Stephen Davies
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

import com.github.aushacker.eagle2nc.xml.XPackage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Wrapper for the XML Package type.
 *
 * @author Stephen Davies
 * @since February 2022
 */
public class Package {

    /**
     * Wrapped XML Package.
     */
    private XPackage pkg;

    public String getDescription() {
        return pkg.getDescription();
    }

    public String getName() {
        return pkg.getName();
    }

    public Collection<Pad> getPads() {
        // TODO
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Package(\"");
        sb.append(getName());
        sb.append("\"");

        return sb.toString();
    }
}
