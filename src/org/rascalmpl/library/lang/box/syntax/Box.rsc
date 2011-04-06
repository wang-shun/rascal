@license{
  Copyright (c) 2009-2011 CWI
  All rights reserved. This program and the accompanying materials
  are made available under the terms of the Eclipse Public License v1.0
  which accompanies this distribution, and is available at
  http://www.eclipse.org/legal/epl-v10.html
}
@contributor{Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI}
@contributor{Bert Lisser - Bert.Lisser@cwi.nl (CWI)}

module lang::box::syntax::Box

start syntax Main = Boxx WhitespaceAndComment*;

syntax Boxx
        = StrCon
        | BoxOperator operator "[" Boxx* list "]"
        | FontOperator operator "[" Boxx* list "]"
        | "LBL" "[" StrCon "," Boxx "]"
        | "REF" "[" StrCon "," Boxx "]"
        | "CNT" "[" StrCon "," StrCon "]"
       /* | "O" SOptions "[" Boxx BoxString Boxx "]" */
 ;
 

syntax StrCon
	= lex [\"] StrChar* chars [\"] ;
	
syntax StrChar
    = lex "\\" [\" \' \< \> \\ b f n r t] 
    | lex ![\" \' \< \> \\] ;
	

syntax NatCon = lex [0-9]+ ;

syntax BoxOperator
        = "A" AlignmentOptions alignments SpaceOption* options
        | "R"
        | "H" SpaceOption* options
        | "V" SpaceOption* options
        | "HV" SpaceOption* options
        | "HOV" SpaceOption* options
        | "I" SpaceOption* options
        | "WD"
        /*
        | "COMM"
        | "F" FontOption* options
        | "G" GroupOption* options
        | "SL" GroupOption* options
        */
 ;
 
 syntax FontOperator
        = "KW"
        | "VAR"
        | "NUM"
        | "MATH"
        | "ESC"
        | "COMM"
        | "STRING"
 ;
 
syntax AlignmentOption
        = "l" SpaceOption* options
        | "c" SpaceOption* options
        | "r" SpaceOption* options
 ;
syntax AlignmentOptions
        =
        "(" {AlignmentOption ","}* ")"
 ;
syntax SpaceSymbol
        = "hs"
        | "vs"
        | "is"
        | "ts"
 ;
syntax SpaceOption
        =
        SpaceSymbol "=" NatCon
 ;

syntax Context
        = "H"
        | "V"
 ;
/*
syntax FontValue
        = NatCon
        | FontId
 ;
syntax FontOption
        =
        FontParam "=" FontValue
 ;
syntax FontParam
        = "fn"
        | "fm"
        | "se"
        | "sh"
        | "sz"
        | "cl"
 ;

 */
syntax GroupOption
        = "gs" "=" NatCon
        | "op" "=" BoxOperator
 ;
 
 layout WhiteSpace =
            WhitespaceAndComment*
            #[\ \t\n\r]
            #"%"
            ;
            
syntax WhitespaceAndComment= lex [\ \t\n\r]
                           | lex "%" [!%]* "%"
                           | lex "%%" [!\n]* "\n"
                           ;
