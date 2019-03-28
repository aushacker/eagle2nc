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

package com.github.aushacker.eagle2nc.nc;

import java.io.PrintStream;

/**
 * PCB mills can have their Z axis controlled by stepper or solenoid. This
 * interface allow for this abstraction. See GOF Strategy pattern.
 *
 * @author Stephen Davies
 * @since March 2019
 */
public interface ZStrategy {

	/**
	 * A 'high' clearance, suitable for changing tools.
	 */
	public void toolChange(PrintStream out);

	public void engraveRough(PrintStream out);

	public void engraveFinish(PrintStream out);

	public boolean supportsMultiplePasses();

	public void drill(PrintStream out);
}
