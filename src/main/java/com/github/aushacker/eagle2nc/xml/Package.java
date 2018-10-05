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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * @author Stephen Davies
 * @since October 2018
 */
public class Package {

	// ID
	@XmlAttribute
	private String name;

	@XmlElement
	private String description;
	
	@XmlElements({
		@XmlElement(name="circle", type=Circle.class),
		@XmlElement(name="pad", type=Pad.class),
		@XmlElement(name="polygon", type=Polygon.class),
		@XmlElement(name="rectangle", type=Rectangle.class),
		@XmlElement(name="smd", type=Smd.class),
		@XmlElement(name="wire", type=Wire.class)
	})
	private List<GraphicElement> elements;

	public String getDescription() {
		return description;
	}

	public List<GraphicElement> getElements() {
		return elements;
	}

	public String getName() {
		return name;
	}
}
