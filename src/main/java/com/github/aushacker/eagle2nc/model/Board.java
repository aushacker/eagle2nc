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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.aushacker.eagle2nc.xml.XEagle;
import com.github.aushacker.eagle2nc.xml.XLibrary;

/**
 * Top level model type. Wraps XML data to provide higher
 * level information.
 * 
 * @author Stephen Davies
 * @since October 2018
 */
public class Board {

	private XEagle xmlModel;

	//private Dimensions dimensions;

	private Map<String, Library> libraries;

	public Board(XEagle xmlModel) {
		this.xmlModel = xmlModel;
		//this.dimensions = new Dimensions(this);
	}

	public Collection<Library> getLibraries() {
		if (libraries == null) {
			initialiseLibraries();
		}

		return libraries.values();
	}

	public XEagle getXmlModel() {
		return xmlModel;
	}

	private void initialiseLibraries() {
		libraries = new HashMap<>();
		for (XLibrary xLib : xmlModel.getLibraries()) {
			libraries.put(xLib.getName(), new Library(xLib));
		}
	}
}
