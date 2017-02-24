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
 * 	it under the terms of the GNU General Public License as published by
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
 * A default implementation of the DispatchHandler interface specifying
 * the handle return type as Object.
 * 
 * Given an Object o of indeterminate type and an implementation of Visitor
 * then the appropriate handle or dispatch method will be dynamically selected
 * at run time
 * 	
 *  DefaultDispatchHandler dispatcher = new DefaultDispatchHandler();
 * 
 * 	Visitor v;
 * 	Object o = "a String";
 * 
 * 	dispatcher.dispatch(v,o);
 * 
 * In the above example a method with signature 
 * 
 * 	void dispatch(String s) 
 * 
 * will be invoked if one exists. If one does not exist then the most
 * appropriate method will be selected where the lower bound is the default
 * method in the Visitor interface.
 * 
 * @author Simon Baird
 *
 */
class DefaultDispatchHandler extends DispatchHandler<Object>   {
}

