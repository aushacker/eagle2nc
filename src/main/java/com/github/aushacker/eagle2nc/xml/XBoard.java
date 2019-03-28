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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

/**
 * @author Stephen Davies
 * @since October 2018
 */
public class XBoard {

	/**
	 * GraphicElements that have been placed directly on the board itself, rather than
	 * nested within a Package.
	 *
	 * Examples include PCB Dimension layer and top level text (silkscreen).
	 */
	@XmlElementWrapper(name="plain")
	@XmlElements({
		@XmlElement(name="circle", type=XCircle.class),
		@XmlElement(name="hole", type=XHole.class),
		@XmlElement(name="rectangle", type=XRectangle.class),
		@XmlElement(name="wire", type=XWire.class)
	})
	private List<XGraphicElement> plain;

	/**
	 * Fragments (Packages) copied from the component libraries.
	 */
	@XmlElementWrapper(name="libraries")
	@XmlElement(name="library")
	private List<XLibrary> libraries;

	/**
	 * Design elements i.e. components placed on the board.
	 */
	@XmlElementWrapper(name="elements")
	@XmlElement(name="element")
	private List<XElement> elements;

	/**
	 * Traces and their pad connections.
	 */
	@XmlElementWrapper(name="signals")
	@XmlElement(name="signal")
	private List<XSignal> signals;

	public List<XElement> getElements() {
		return elements;
	}

	/**
	 * Convenience method.
	 *
	 * @return Returns all top level XHole objects nested in the &lt;plain&gt; element.
	 */
	public Collection<XHole> getHoles() {
		return getPlain()
			.stream()
			.filter(e -> e.isHole())
			.map(e -> (XHole) e)
			.collect(Collectors.toList());
	}

	public List<XLibrary> getLibraries() {
		if (libraries == null) {
			libraries = new ArrayList<>();
		}

		return libraries;
	}

	public List<XGraphicElement> getPlain() {
		if (plain == null) {
			plain = new ArrayList<>();
		}

		return plain;
	}

	public List<XGraphicElement> getPlainWires() {
		if (plain == null) {
			plain = new ArrayList<>();
		}

		return plain;
	}

	public List<XSignal> getSignals() {
		if (signals == null) {
			signals = new ArrayList<>();
		}

		return signals;
	}

	/**
	 * Convenience method.
	 *
	 * @return
	 */
	public Collection<XVia> getVias() {
		ArrayList<XVia> vias = new ArrayList<>();

		getSignals()
			.forEach(s -> s.getVias()
					.forEach(v -> vias.add(v)));

		return vias;
	}
}
