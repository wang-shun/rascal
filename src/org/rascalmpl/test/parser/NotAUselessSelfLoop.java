/*******************************************************************************
 * Copyright (c) 2009-2011 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Arnold Lankamp - Arnold.Lankamp@cwi.nl
*******************************************************************************/
package org.rascalmpl.test.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.io.StandardTextReader;
import org.rascalmpl.parser.gtd.SGTDBF;
import org.rascalmpl.parser.gtd.stack.AbstractStackNode;
import org.rascalmpl.parser.gtd.stack.LiteralStackNode;
import org.rascalmpl.parser.gtd.stack.NonTerminalStackNode;
import org.rascalmpl.values.ValueFactoryFactory;
import org.rascalmpl.values.uptr.Factory;

/*
S ::= AA | B
A ::= CC | a
B ::= AA | CC
C ::= AA | a
*/
public class NotAUselessSelfLoop extends SGTDBF implements IParserTest{
	private final static IConstructor SYMBOL_START_S = VF.constructor(Factory.Symbol_Sort, VF.string("S"));
	private final static IConstructor SYMBOL_A = VF.constructor(Factory.Symbol_Sort, VF.string("A"));
	private final static IConstructor SYMBOL_B = VF.constructor(Factory.Symbol_Sort, VF.string("B"));
	private final static IConstructor SYMBOL_C = VF.constructor(Factory.Symbol_Sort, VF.string("C"));
	private final static IConstructor SYMBOL_a = VF.constructor(Factory.Symbol_Lit, VF.string("a"));
	private final static IConstructor SYMBOL_char_a = VF.constructor(Factory.Symbol_CharClass, VF.list(VF.constructor(Factory.CharRange_Single, VF.integer(97))));
	
	private final static IConstructor PROD_S_AA = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_A, SYMBOL_A), SYMBOL_START_S, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_S_B = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_B), SYMBOL_START_S, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_A_CC = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_C, SYMBOL_C), SYMBOL_A, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_A_a = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_a), SYMBOL_A, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_B_AA = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_A, SYMBOL_A), SYMBOL_B, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_B_CC = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_C, SYMBOL_C), SYMBOL_B, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_C_AA = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_A, SYMBOL_A), SYMBOL_C, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_C_a = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_a), SYMBOL_C, VF.constructor(Factory.Attributes_NoAttrs));
	private final static IConstructor PROD_a_a = VF.constructor(Factory.Production_Default, VF.list(SYMBOL_char_a), SYMBOL_a, VF.constructor(Factory.Attributes_NoAttrs));
	
	private final static AbstractStackNode NONTERMINAL_START_S = new NonTerminalStackNode(AbstractStackNode.START_SYMBOL_ID, 0, "S");
	private final static AbstractStackNode NONTERMINAL_A0 = new NonTerminalStackNode(0, 0, "A");
	private final static AbstractStackNode NONTERMINAL_A1 = new NonTerminalStackNode(1, 1, "A");
	private final static AbstractStackNode NONTERMINAL_A2 = new NonTerminalStackNode(2, 0, "A");
	private final static AbstractStackNode NONTERMINAL_A3 = new NonTerminalStackNode(3, 1, "A");
	private final static AbstractStackNode NONTERMINAL_A4 = new NonTerminalStackNode(4, 0, "A");
	private final static AbstractStackNode NONTERMINAL_A5 = new NonTerminalStackNode(5, 1, "A");
	private final static AbstractStackNode NONTERMINAL_B6 = new NonTerminalStackNode(6, 0, "B");
	private final static AbstractStackNode NONTERMINAL_C7 = new NonTerminalStackNode(7, 0, "C");
	private final static AbstractStackNode NONTERMINAL_C8 = new NonTerminalStackNode(8, 1, "C");
	private final static AbstractStackNode NONTERMINAL_C9 = new NonTerminalStackNode(9, 0, "C");
	private final static AbstractStackNode NONTERMINAL_C10 = new NonTerminalStackNode(10, 1, "C");
	private final static AbstractStackNode LITERAL_a11 = new LiteralStackNode(11, 0, PROD_a_a, new char[]{'a'});
	private final static AbstractStackNode LITERAL_a12 = new LiteralStackNode(12, 0, PROD_a_a, new char[]{'a'});
	
	public NotAUselessSelfLoop(){
		super();
	}
	
	public void S(){
		expect(PROD_S_AA, NONTERMINAL_A0, NONTERMINAL_A1);
		
		expect(PROD_S_B, NONTERMINAL_B6);
	}
	
	public void A(){
		expect(PROD_A_CC, NONTERMINAL_C7, NONTERMINAL_C8);
		
		expect(PROD_A_a, LITERAL_a11);
	}
	
	public void B(){
		expect(PROD_B_AA, NONTERMINAL_A2, NONTERMINAL_A3);

		expect(PROD_B_CC, NONTERMINAL_C9, NONTERMINAL_C10);
	}
	
	public void C(){
		expect(PROD_C_AA, NONTERMINAL_A4, NONTERMINAL_A5);
		
		expect(PROD_C_a, LITERAL_a12);
	}
	
	public IConstructor executeParser(){
		return parse(NONTERMINAL_START_S, null, "aaa".toCharArray());
	}
	
	public IValue getExpectedResult() throws IOException{
		String expectedInput = "amb({appl(prod([sort(\"A\"),sort(\"A\")],sort(\"S\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([sort(\"C\"),sort(\"C\")],sort(\"A\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])])]),appl(prod([sort(\"B\")],sort(\"S\"),\\no-attrs()),[amb({appl(prod([sort(\"A\"),sort(\"A\")],sort(\"B\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([sort(\"C\"),sort(\"C\")],sort(\"A\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])])]),appl(prod([sort(\"C\"),sort(\"C\")],sort(\"B\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([sort(\"A\"),sort(\"A\")],sort(\"C\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])])]),appl(prod([sort(\"C\"),sort(\"C\")],sort(\"B\"),\\no-attrs()),[appl(prod([sort(\"A\"),sort(\"A\")],sort(\"C\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])]),appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])]),appl(prod([sort(\"A\"),sort(\"A\")],sort(\"B\"),\\no-attrs()),[appl(prod([sort(\"C\"),sort(\"C\")],sort(\"A\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])]),appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])])})]),appl(prod([sort(\"A\"),sort(\"A\")],sort(\"S\"),\\no-attrs()),[appl(prod([sort(\"C\"),sort(\"C\")],sort(\"A\"),\\no-attrs()),[appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])]),appl(prod([lit(\"a\")],sort(\"C\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])]),appl(prod([lit(\"a\")],sort(\"A\"),\\no-attrs()),[appl(prod([\\char-class([single(97)])],lit(\"a\"),\\no-attrs()),[char(97)])])])})";
		return new StandardTextReader().read(ValueFactoryFactory.getValueFactory(), Factory.uptr, Factory.Tree, new ByteArrayInputStream(expectedInput.getBytes()));
	}

	public static void main(String[] args){
		NotAUselessSelfLoop nausl = new NotAUselessSelfLoop();
		IConstructor result = nausl.parse(NONTERMINAL_START_S, null, "aaa".toCharArray());
		System.out.println(result);
		
		System.out.println("[S(A(a),A(C(a),C(a))),S([B(A(C(a),C(a)),A(a)),B(A(a),A(C(a),C(a))),B(C(a),C(A(a),A(a))),B(C(A(a),A(a)),C(a))]),S(A(C(a),C(a)),A(a))] <- good");
	}
}
