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
 * The dispatch handler dynamically routes calls to the Visitor. This 
 * circumvents double-dispatch required when implementing the Visitor 
 * pattern and thus removes the need for target object to implement any 
 * interfaces.
 * 
 * Typically an extension of this class will specify Object as a return type
 * parameter. If an specialisation of this is required to enforce static 
 * typing then it may be supplied in the class parameter.
 * 
 * DispatchHandler stringDispatcher = new DispatchHandler<String>();
 * 
 * String result = stringDispatcher.handle(v, o);
 * 
 * 
 * @author Simon Baird
 *
 * @param <T> the return type from the Visitor implementation
 */
class DispatchHandler<T>   {

	/**
	 * Forward the Object under extension to the supplied Visitor. 
	 * 
	 * @param v a Visitor object that dynamically extends the Object o
	 * @param o any Object requiring dynamic extension
	 */
	public void dispatch(Visitor v, Object  o) {

		v.dispatch(o);
	}

	/**
	 * Returns the output from calling the appropriate method on the
	 * supplied Vistor. The return type is enforceable by parameter
	 * 
	 * @param v a Visitor object that dynamically extends the Object o
	 * @param o o any Object requiring dynamic extension
	 * @return an Object of type T returned from the Visitor method
	 */
	public T handle(Visitor v, Object  o) {

		return v.handle(o);
	}
}

