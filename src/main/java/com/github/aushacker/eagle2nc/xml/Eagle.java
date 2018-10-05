package com.github.aushacker.eagle2nc.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stephen Davies
 * @since October 2018
 */
@XmlRootElement
public class Eagle {

	@XmlAttribute
	private String version;

	@XmlElement
	private Drawing drawing;
	
	public Drawing getDrawing() {
		return drawing;
	}

	public String getVersion() {
		return version;
	}

}
