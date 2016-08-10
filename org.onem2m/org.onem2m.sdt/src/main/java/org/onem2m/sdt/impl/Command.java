/*******************************************************************************
 * Copyright (c) 2014, 2016 Orange.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.onem2m.sdt.impl;

import java.util.Map;

import org.onem2m.sdt.Action;
import org.onem2m.sdt.utils.Activator;

public abstract class Command extends Action {

	public Command(String name, String definition) {
		super(name, definition);
	}
	
	protected void check() throws ActionException, AccessException {
		if (! Activator.isGrantedAccess(this))
			throw new AccessException("No access allowed for action " + getName());
	}
	
	/**
	 * This method is used to invoke the action in a generic way.
	 * Typically, it is used by the SDT IPE
	 * 
	 * @param arguments arguments to be used
	 * @return output
	 * @throws ActionException
	 * @throws AccessException
	 */
	public abstract Object invoke(Map<String, Object> arguments) throws ActionException, AccessException;

}