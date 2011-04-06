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
package org.rascalmpl.interpreter.staticErrors;

import org.rascalmpl.ast.AbstractAST;

public class RedeclaredTypeError extends StaticError {
	private static final long serialVersionUID = 4898431127235860576L;

	public RedeclaredTypeError(String name, AbstractAST decl) {
		super("Illegal re-declaration of type: " + name, decl);
	}

}
