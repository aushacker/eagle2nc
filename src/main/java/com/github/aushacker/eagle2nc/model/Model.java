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

import java.util.ArrayList;
import java.util.List;

import com.github.aushacker.eagle2nc.xml.GraphicElement;
import com.github.aushacker.eagle2nc.xml.Wire;

/**
 * @author Stephen Davies
 * @since October 2018
 */
public abstract class Model {

	private Board board;

	public Model(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}

	protected List<Wire> extractDimensions() {
		List<Wire> wires = new ArrayList<Wire>();
		
		for (GraphicElement e : getDrawingBoard().getPlain()) {
			if (e.isWire() && ((Wire)e).getLayer().equals(Layer.DIMENSION.getId())) {
				wires.add((Wire)e);
			}
		}
		return wires;
	}

	protected com.github.aushacker.eagle2nc.xml.Board getDrawingBoard() {
		return board.getXmlModel().getDrawing().getBoard();
	}
}
