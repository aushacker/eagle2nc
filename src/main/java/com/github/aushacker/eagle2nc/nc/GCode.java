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

/**
 * Hardcoded, constant Gcode values.
 *
 * @author Stephen Davies
 * @since March 2019
 */
public class GCode {

    public static final String RAPID = "G0";
    public static final String LINEAR = "G1";

    /**
     * Miscellaneous Functions
     */
    public static final String SPINDLE_CW = "M03";
    public static final String SPINDLE_CCW = "M04";
    public static final String SPINDLE_STOP = "M05";
    public static final String ATC = "M06";
    public static final String COOLANT_MIST = "M07";
    public static final String COOLANT_FLOOD = "M08";
    public static final String COOLANT_OFF = "M09";
    public static final String PROGRAM_END = "M30";

    /**
     * Prevent instantiation.
     */
    private GCode() {}
}
