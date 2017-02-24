/*
 *       _ _                 _       _           _                     _ _
 *    __| (_)___ _ __   __ _| |_ ___| |__       | |__   __ _ _ __   __| | | ___ _ __
 *   / _` | / __| '_ \ / _` | __/ __| '_ \ _____| '_ \ / _` | '_ \ / _` | |/ _ \ '__|
 *  | (_| | \__ \ |_) | (_| | || (__| | | |_____| | | | (_| | | | | (_| | |  __/ |
 *   \__,_|_|___/ .__/ \__,_|\__\___|_| |_|     |_| |_|\__,_|_| |_|\__,_|_|\___|_|
 *              |_|
 *
 * 
 * 	(http://patorjk.com/software/taag/	Font: Irvin)
 *
 *	Copyright 2017 Simon Baird.  All Rights Reserved.
 * 	DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *	This file is part of dispatch-handler.
 *
 * 	dispatch-handler is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	dispatch-handler is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License included
 * 	in the LICENSE file that accompanied this code.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.bac.dispatcher;

/**
 * Specifies methods allowing a class to extend existing object structures in
 * the manner of the Visitor pattern.
 * 
 * @author Simon Baird
 *
 */
public interface Visitor {

	/**
	 * Forwards the supplied object to the appropriate visitor. This is
	 * differentiated from the handle method in that its return signature is
	 * void
	 * 
	 * @param o
	 *            any {@code Object} that requires extension
	 */
	default void dispatch(Object o) {

		// No operation
	};

	/**
	 * Returns a value from the relevant Visitor instantiation. This default
	 * implementation always returns null.
	 * 
	 * @param o
	 *            any {@code Object} that requires extension
	 * @return null
	 */
	default Object handle(Object o) {

		return null;
	}
}
