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

/**
 * A bunch of JAXB annotated types to deserialise an Eagle board file. Types
 * declared here correspond 1 to 1 with the XML structure within the Eagle
 * board file.
 * <p>
 * Types are prefixed with an 'X' to prevent name clashes with those
 * in the model package.
 *
 * @author Stephen Davies
 * @since October 2018
 */
@XmlAccessorType(XmlAccessType.FIELD)
package com.github.aushacker.eagle2nc.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
