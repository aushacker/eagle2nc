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

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Top-level XML element in the Eagle board file.
 * <p>
 * Not much useful detail at this level, much of this can be found in
 * the &ltldrawing&gt; element.
 *
 * @author Stephen Davies
 * @since October 2018
 */
@XmlRootElement (name = "eagle")
public class XEagle {

	@XmlAttribute
	private String version;

	@XmlElement
	private XDrawing drawing;
	
	public XDrawing getDrawing() {
		return drawing;
	}

	/**
	 * Convenience method.
	 *
	 * @return Returns all top level XHole objects nested in the &lt;plain&gt; element.
	 */
	public Collection<XHole> getHoles() {
		return getDrawing().getBoard().getHoles();
	}

	/**
	 * Convenience method.
	 *
	 * @return Eagle component library fragments included in the board.
	 */
	public Collection<XLibrary> getLibraries() {
		return getDrawing().getBoard().getLibraries();
	}

	public String getVersion() {
		return version;
	}

	public Collection<XVia> getVias() {
		return getDrawing().getBoard().getVias();
	}
}
