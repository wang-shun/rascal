package org.meta_environment.rascal.interpreter.load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FromResourceLoader extends AbstractModuleLoader {

	private final String sourceFolder;
	private final Class<?> clazz;

	/**
	 * Creates a loader that will find modules in the resources
	 * of the given class
	 * 
	 * @param clazz
	 */
	public FromResourceLoader(Class<?> clazz) {
		this(clazz, "");
	}
	
	/**
	 * Creates a loader that will find modules in the resources
	 * of the given class. The given sourceFolder is where the search
	 * for modules in the class's resources will start.
	 * 
	 * @param clazz
	 */
	public FromResourceLoader(Class<?> clazz, String sourceFolder) {
		this.clazz = clazz;
		
		if (!sourceFolder.startsWith("/")) {
			sourceFolder = "/" + sourceFolder;
		}
		if (!sourceFolder.endsWith("/")) {
			sourceFolder = sourceFolder + "/";
		}
		this.sourceFolder = sourceFolder;
	}

	@Override
	protected InputStream getInputStream(String name) throws IOException {
		URL url = clazz.getResource(sourceFolder + name);
		
		if (url == null) {
			throw new FileNotFoundException("File not found: " + name);
		}
		
		return url.openStream();
	}

	@Override
	protected OutputStream getOutputStream(String name) throws IOException {
		return new FileOutputStream("./bin/" + sourceFolder + name); // Meh, can't output to 'file://' urls for some rediculious reason.
	}
}
