/*******************************************************************************
 * Copyright (c) 2009-2011 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Tijs van der Storm - Tijs.van.der.Storm@cwi.nl
*******************************************************************************/
package org.rascalmpl.interpreter.staticErrors;

import org.rascalmpl.ast.AbstractAST;

public class AppendWithoutLoop extends StaticError {
	private static final long serialVersionUID = 4840304460180936759L;

	public AppendWithoutLoop(AbstractAST ast) {
		super("append statement without enclosing loop", ast);
	}
}
