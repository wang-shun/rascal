@license{
  Copyright (c) 2009-2011 CWI
  All rights reserved. This program and the accompanying materials
  are made available under the terms of the Eclipse Public License v1.0
  which accompanies this distribution, and is available at
  http://www.eclipse.org/legal/epl-v10.html
}
@contributor{Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI}
module demo::AbstractPico::AbstractSyntax
/*
 * The abstract syntax of our favourite toy language
 */
public data TYPE =
	  natural() | string();
	  
public alias PicoId = str;

public data EXP = 
      id(PicoId name)
    | natCon(int iVal)
    | strCon(str sVal)
    | add(EXP left, EXP right)
    | sub(EXP left, EXP right)
    | conc(EXP left, EXP right)
    ;
    
public data STATEMENT =
	  asgStat(PicoId name, EXP exp)
	| ifStat(EXP exp, list[STATEMENT] thenpart, list[STATEMENT] elsepart)
	| whileStat(EXP exp, list[STATEMENT] body)
	;
    
public data DECL =
	  decl(PicoId name, TYPE tp);

public data PROGRAM =
	  program(list[DECL] decls, list[STATEMENT] stats);
	  
/*
 * Define annotations on expressions and statements to be used in the
 * controlflow graph
 */

anno int EXP@pos;
anno int STATEMENT@pos;
