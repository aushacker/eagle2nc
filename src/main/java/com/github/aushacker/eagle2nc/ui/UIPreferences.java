/*
 * Copyright 2021 Stephen Davies
 *
 * This file is part of eagle2nc.
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
 * along with eagle2nc. If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.aushacker.eagle2nc.ui;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * User configurable UI preferences, things the user can adjust like font
 * sizes and colours.
 *
 * @author Stephen Davies
 * @since April 2021
 */
public class UIPreferences {

	private static final String HEIGHT = "height";
	private static final int DEFAULT_HEIGHT = 400;
	private static final String WIDTH = "width";
	private static final int DEFAULT_WIDTH = 800;
	
	/**
	 * Directory containing gcode scripts. 
	 */
	private static final String SCRIPT_HOME = "scriptHome";
	private static final String INITIAL_SCRIPT = "initialScript";

	private static Preferences prefs = Preferences.userNodeForPackage(UIPreferences.class);
	
	public int getHeight() {
		return prefs.getInt(HEIGHT, DEFAULT_HEIGHT);
	}

	public int getWidth() {
		return prefs.getInt(WIDTH, DEFAULT_WIDTH);
	}

	public void setHeight(int height) {
		prefs.putInt(HEIGHT, height);
	}

	public void setInitialScript(String initialScript) {
		prefs.put(INITIAL_SCRIPT, initialScript);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void setScriptHome(String scriptHome) {
		prefs.put(SCRIPT_HOME, scriptHome);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public void setWidth(int width) {
		prefs.putInt(WIDTH, width);
	}
}
