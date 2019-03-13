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

/**
 * In Eagle, the information for drilling holes can come from a number
 * of places in the .brd file. Top level holes, those placed using the Eagle Hole command, are located
 * within the &lt;plain&gt; element. Other holes are implied by certain shapes, i.e. pads and vias.
 * <p>
 * Hide the actual information source behind an abstract facade.
 * 
 * @author Stephen Davies
 * @since March 2019
 */
public interface DrillHole {

	public double getDrill();

	public double getX();

	public double getY();

}
