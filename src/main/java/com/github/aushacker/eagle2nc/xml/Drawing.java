package com.github.aushacker.eagle2nc.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stephen Davies
 * @since October 2018
 */
@XmlRootElement
public class Drawing {

	@XmlElementWrapper(name="layers")
	@XmlElement(name="layer")
	private List<Layer> layers;
}
