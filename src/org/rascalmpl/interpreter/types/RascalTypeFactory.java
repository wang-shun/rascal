/*******************************************************************************
 * Copyright (c) 2009-2011 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
*******************************************************************************/
package org.rascalmpl.interpreter.types;

import java.util.Set;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;

public class RascalTypeFactory {
	private TypeFactory tf = TypeFactory.getInstance();

	private static class InstanceHolder {
		public static final RascalTypeFactory sInstance = new RascalTypeFactory();
	}
	
	public static RascalTypeFactory getInstance() {
		return InstanceHolder.sInstance;
	}
	
	public Type functionType(Type returnType, Type argumentTypes) {
		return tf.externalType(new FunctionType(returnType, argumentTypes));
	}
	
	public Type nonTerminalType(IConstructor cons) {
		return tf.externalType(new NonTerminalType(cons));
	}
	
	public Type nonTerminalType(org.rascalmpl.ast.Type symbol) {
		return tf.externalType(new NonTerminalType(symbol));
	}
	
	public Type overloadedFunctionType(Set<FunctionType> newAlternatives) {
		return tf.externalType(new OverloadedFunctionType(newAlternatives));
	}

	public Type reifiedType(Type arg) {
		return tf.externalType(new ReifiedType(arg));
	}
	
}
