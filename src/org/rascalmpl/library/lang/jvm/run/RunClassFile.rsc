@license{
  Copyright (c) 2009-2011 CWI
  All rights reserved. This program and the accompanying materials
  are made available under the terms of the Eclipse Public License v1.0
  which accompanies this distribution, and is available at
  http://www.eclipse.org/legal/epl-v10.html
}
@contributor{Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI}
@contributor{Atze van der Ploeg - Atze.van.der.Ploeg@cwi.nl (CWI)}
module lang::jvm::run::RunClassFile

@doc{Register the class file, and its dependencies and run it's main method}
@javaClass{org.rascalmpl.library.lang.jvm.run.RunClassFile}
@reflect{Uses URI Resolver Registry}
public void java runClassFile(loc path,loc dependencies...)
throws PathNotFound(loc), IOError(str msg);
