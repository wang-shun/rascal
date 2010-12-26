package org.rascalmpl.library.vis.compose;

import org.eclipse.imp.pdb.facts.IList;
import org.rascalmpl.interpreter.IEvaluatorContext;
import org.rascalmpl.library.vis.Figure;
import org.rascalmpl.library.vis.FigurePApplet;
import org.rascalmpl.library.vis.properties.IPropertyManager;


/**
 * 
 * Overlay elements by stacking them (aligned around their anchor point).
 * 
 * @author paulk
 *
 */
public class Overlay extends Compose {
	
	private static boolean debug = false;
	float topAnchor = 0;
	float bottomAnchor = 0;
	float leftAnchor = 0;
	float rightAnchor = 0;

	public Overlay(FigurePApplet fpa, IPropertyManager properties, IList elems, IEvaluatorContext ctx) {
		super(fpa, properties, elems, ctx);
	}
	
	@Override
	public
	void bbox(){
		
		topAnchor = bottomAnchor = leftAnchor = rightAnchor = 0;
		
		for(Figure ve : figures){
			ve.bbox();
			topAnchor = max(topAnchor, ve.topAnchor());
			bottomAnchor = max(bottomAnchor, ve.bottomAnchor());
			leftAnchor = max(leftAnchor, ve.leftAnchor());
			rightAnchor = max(rightAnchor, ve.rightAnchor());
		}
		width = leftAnchor + rightAnchor;
		height = topAnchor + bottomAnchor;
		if(debug)System.err.printf("overlay.bbox: width=%f, height=%f\n", width, height);
	}
	
	@Override
	public
	void draw(float left, float top) {
		this.left = left;
		this.top = top;
		
		applyProperties();
		if(debug)System.err.printf("overlay.draw: left=%f, top=%f\n", left, top);
		for(Figure ve : figures){	
			//ve.drawAnchor(left + leftAnchor, top + topAnchor);
			ve.draw(left + leftAnchor - ve.leftAnchor(), top + topAnchor - ve.topAnchor());
		}
	}

}
