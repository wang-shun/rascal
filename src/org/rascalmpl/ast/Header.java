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
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *   * Mark Hills - Mark.Hills@cwi.nl (CWI)
 *   * Arnold Lankamp - Arnold.Lankamp@cwi.nl
*******************************************************************************/

package org.rascalmpl.ast;


import org.eclipse.imp.pdb.facts.INode;

import org.rascalmpl.interpreter.asserts.Ambiguous;

import org.eclipse.imp.pdb.facts.IConstructor;

import org.eclipse.imp.pdb.facts.IValue;

import org.rascalmpl.interpreter.BooleanEvaluator;

import org.rascalmpl.interpreter.Evaluator;

import org.rascalmpl.interpreter.PatternEvaluator;

import org.rascalmpl.interpreter.asserts.Ambiguous;

import org.rascalmpl.interpreter.env.Environment;

import org.rascalmpl.interpreter.matching.IBooleanResult;

import org.rascalmpl.interpreter.matching.IMatchingResult;

import org.rascalmpl.interpreter.result.Result;


public abstract class Header extends AbstractAST {
  public Header(INode node) {
    super(node);
  }
  

  public boolean hasImports() {
    return false;
  }

  public java.util.List<org.rascalmpl.ast.Import> getImports() {
    throw new UnsupportedOperationException();
  }

  public boolean hasTags() {
    return false;
  }

  public org.rascalmpl.ast.Tags getTags() {
    throw new UnsupportedOperationException();
  }

  public boolean hasParams() {
    return false;
  }

  public org.rascalmpl.ast.ModuleParameters getParams() {
    throw new UnsupportedOperationException();
  }

  public boolean hasName() {
    return false;
  }

  public org.rascalmpl.ast.QualifiedName getName() {
    throw new UnsupportedOperationException();
  }


static public class Ambiguity extends Header {
  private final java.util.List<org.rascalmpl.ast.Header> alternatives;

  public Ambiguity(INode node, java.util.List<org.rascalmpl.ast.Header> alternatives) {
    super(node);
    this.alternatives = java.util.Collections.unmodifiableList(alternatives);
  }

  @Override
  public Result<IValue> interpret(Evaluator __eval) {
    throw new Ambiguous((IConstructor) this.getTree());
  }
  
  @Override
  public org.eclipse.imp.pdb.facts.type.Type typeOf(Environment env) {
    throw new Ambiguous((IConstructor) this.getTree());
  }
  
  @Override
  public IBooleanResult buildBooleanBacktracker(BooleanEvaluator __eval) {
    throw new Ambiguous((IConstructor) this.getTree());
  }

  @Override
  public IMatchingResult buildMatcher(PatternEvaluator __eval) {
    throw new Ambiguous((IConstructor) this.getTree());
  }
  
  public java.util.List<org.rascalmpl.ast.Header> getAlternatives() {
   return alternatives;
  }

  public <T> T accept(IASTVisitor<T> v) {
	return v.visitHeaderAmbiguity(this);
  }
}





  public boolean isParameters() {
    return false;
  }
  
static public class Parameters extends Header {
  // Production: sig("Parameters",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.QualifiedName","name"),arg("org.rascalmpl.ast.ModuleParameters","params"),arg("java.util.List\<org.rascalmpl.ast.Import\>","imports")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.QualifiedName name;
  
     private final org.rascalmpl.ast.ModuleParameters params;
  
     private final java.util.List<org.rascalmpl.ast.Import> imports;
  

  
public Parameters(INode node , org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.QualifiedName name,  org.rascalmpl.ast.ModuleParameters params,  java.util.List<org.rascalmpl.ast.Import> imports) {
  super(node);
  
    this.tags = tags;
  
    this.name = name;
  
    this.params = params;
  
    this.imports = imports;
  
}


  @Override
  public boolean isParameters() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitHeaderParameters(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.QualifiedName getName() {
        return this.name;
     }
     
     @Override
     public boolean hasName() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.ModuleParameters getParams() {
        return this.params;
     }
     
     @Override
     public boolean hasParams() {
        return true;
     }
  
     @Override
     public java.util.List<org.rascalmpl.ast.Import> getImports() {
        return this.imports;
     }
     
     @Override
     public boolean hasImports() {
        return true;
     }
  	
}


  public boolean isDefault() {
    return false;
  }
  
static public class Default extends Header {
  // Production: sig("Default",[arg("org.rascalmpl.ast.Tags","tags"),arg("org.rascalmpl.ast.QualifiedName","name"),arg("java.util.List\<org.rascalmpl.ast.Import\>","imports")])

  
     private final org.rascalmpl.ast.Tags tags;
  
     private final org.rascalmpl.ast.QualifiedName name;
  
     private final java.util.List<org.rascalmpl.ast.Import> imports;
  

  
public Default(INode node , org.rascalmpl.ast.Tags tags,  org.rascalmpl.ast.QualifiedName name,  java.util.List<org.rascalmpl.ast.Import> imports) {
  super(node);
  
    this.tags = tags;
  
    this.name = name;
  
    this.imports = imports;
  
}


  @Override
  public boolean isDefault() { 
    return true; 
  }

  @Override
  public <T> T accept(IASTVisitor<T> visitor) {
    return visitor.visitHeaderDefault(this);
  }
  
  
     @Override
     public org.rascalmpl.ast.Tags getTags() {
        return this.tags;
     }
     
     @Override
     public boolean hasTags() {
        return true;
     }
  
     @Override
     public org.rascalmpl.ast.QualifiedName getName() {
        return this.name;
     }
     
     @Override
     public boolean hasName() {
        return true;
     }
  
     @Override
     public java.util.List<org.rascalmpl.ast.Import> getImports() {
        return this.imports;
     }
     
     @Override
     public boolean hasImports() {
        return true;
     }
  	
}



}
