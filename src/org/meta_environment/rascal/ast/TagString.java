package org.meta_environment.rascal.ast; 
import org.eclipse.imp.pdb.facts.ITree; 
public abstract class TagString extends AbstractAST { 
static public class Lexical extends TagString {
	private String string;
	/*package*/ Lexical(ITree tree, String string) {
		this.tree = tree;
		this.string = string;
	}
	public String getString() {
		return string;
	}

 	public <T> T accept(IASTVisitor<T> v) {
     		return v.visitTagStringLexical(this);
  	}
}
static public class Ambiguity extends TagString {
  private final java.util.List<org.meta_environment.rascal.ast.TagString> alternatives;
  public Ambiguity(ITree tree, java.util.List<org.meta_environment.rascal.ast.TagString> alternatives) {
	this.alternatives = java.util.Collections.unmodifiableList(alternatives);
         this.tree = tree;
  }
  public java.util.List<org.meta_environment.rascal.ast.TagString> getAlternatives() {
	return alternatives;
  }
  
  public <T> T accept(IASTVisitor<T> v) {
     return v.visitTagStringAmbiguity(this);
  }
}
}