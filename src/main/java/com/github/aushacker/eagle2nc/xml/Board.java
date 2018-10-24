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

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

/**
 * @author Stephen Davies
 * @since October 2018
 */
public class Board {

	/**
	 * GraphicElements that have been placed directly on the board itself, rather than
	 * nested within a Package.
	 *
	 * Examples include PCB Dimension layer and top level text (silkscreen).
	 */
	@XmlElementWrapper(name="plain")
	@XmlElements({
		@XmlElement(name="circle", type=Circle.class),
		@XmlElement(name="hole", type=Hole.class),
		@XmlElement(name="rectangle", type=Rectangle.class),
		@XmlElement(name="wire", type=Wire.class)
	})
	private List<GraphicElement> plain;

	/**
	 * Fragments (Packages) copied from the component libraries.
	 */
	@XmlElementWrapper(name="libraries")
	@XmlElement(name="library")
	private List<Library> libraries;

	/**
	 * Design elements i.e. components placed on the board.
	 */
	@XmlElementWrapper(name="elements")
	@XmlElement(name="element")
	private List<Element> elements;

	/**
	 * Traces and their pad connections.
	 */
	@XmlElementWrapper(name="signals")
	@XmlElement(name="signal")
	private List<Signal> signals;

	public List<Element> getElements() {
		return elements;
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public List<GraphicElement> getPlain() {
		return plain;
	}

	public List<Signal> getSignals() {
		return signals;
	}
}
