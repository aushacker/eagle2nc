/*
 * Copyright 2019 Stephen Davies
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

import java.util.ArrayList;
import java.util.Collection;

import com.github.aushacker.eagle2nc.xml.XSignal;

/**
 * 
 * @author Stephen Davies
 * @since August 2019
 */
public class Signal {

	private XSignal signal;

	private Collection<Trace> traces;

	private Collection<Via> vias;

	public Signal(XSignal signal) {
		this.signal = signal;
	}
	
	public Collection<Trace> getTraces() {
		if (traces == null) {
			traces = new ArrayList<>();
			signal.getWires().forEach(w -> traces.add(new Trace(w)));
		}

		return traces;
	}

	public Collection<Via> getVias() {
		if (vias == null) {
			vias = new ArrayList<>();
			
			signal.getVias().forEach(xVia -> vias.add(new Via(xVia)));
		}
		
		return vias;
	}
}
