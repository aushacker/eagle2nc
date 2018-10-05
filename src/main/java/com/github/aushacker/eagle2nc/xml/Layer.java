package com.github.aushacker.eagle2nc.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Represents a layer in the Eagle board.
 * For example: dimension (20), top (1), bottom (16) etc.
 *
 * @author Stephen Davies
 * @since October 2018
 */
public class Layer {

	@XmlAttribute
	private String number;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String color;
	
	@XmlAttribute
	private String fill;
	
	@XmlAttribute
	private boolean visible;
	
	@XmlAttribute
	private boolean active;
	
	public boolean getActive() {
		return active;
	}
	
	public String getColor() {
		return color;
	}

	public String getFill() {
		return fill;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public boolean getVisible() {
		return visible;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Layer(");
		result.append(number);
		result.append(", ");
		result.append(name);
		result.append(")");
		return result.toString();
	}
}
