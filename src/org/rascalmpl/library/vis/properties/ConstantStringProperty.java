/*******************************************************************************
 * Copyright (c) 2009-2011 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
*******************************************************************************/
package org.rascalmpl.library.vis.properties;

public class ConstantStringProperty implements IStringPropertyValue {
	Property property;
	String value;

	public ConstantStringProperty(Property prop, String val){
		this.property = prop;
		this.value = val;
	}
	
	public Property getProperty(){
		return property;
	}
	
	public String getValue() {
		return value;
	}

	public boolean isCallBack() {
		return false;
	}

}
