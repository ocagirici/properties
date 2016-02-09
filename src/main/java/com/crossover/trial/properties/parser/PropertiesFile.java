package com.crossover.trial.properties.parser;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.TreeSet;

import com.crossover.trial.properties.property.Property;

public class PropertiesFile {
	
	/**
	 * This class is a PropertiesFile object. Has its own methods of adding and removing properties.
	 * 
	 */
	
	private boolean isURL; 
	private String fileExtension;
	private String fileName;
	private ArrayList<String> lines = new ArrayList<String>();
	private TreeSet<Property> properties = new TreeSet<Property>();


	public PropertiesFile(String filePath) throws IllegalArgumentException {
		
		if(filePath.startsWith("http://") || filePath.startsWith("https://") || filePath.startsWith("ftp://") || filePath.startsWith("ftps://"))
			setURL(true); // supports only four prefixes.
		
		else 
			setURL(false);
		
		
		String ext = ""; // find the extension
		int i = filePath.lastIndexOf('.');
		if (i > 0) 
		    ext = filePath.substring(i+1);
		
		if(ext.compareTo("json") == 0 || ext.compareTo("properties") == 0) { // supports only two extensions.
			setExtension(ext);
		}
		
		else {
			throw new IllegalArgumentException("The file extension should be either "
					+ "'.json' or '.properties'. Extension is " + ext);
		}
	}
	
	/** begin getters and setters **/
	
	public boolean isURL() {
		return isURL;
	}

	public void setURL(boolean isURL) {
		this.isURL = isURL;
	}

	public String getExtension() {
		return fileExtension;
	}

	public void setExtension(String extension) {
		this.fileExtension = extension;
	}
	

	public String getName() {
		return fileName;
	}

	public void setName(String fileName) {
	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}

	public TreeSet<Property> getProperties() {
		return properties;
	}

	public void setProperties(TreeSet<Property> properties) {
		this.properties = properties;
	}
	
	/** end getters and setters **/
	
	protected void addProperty(Property property){
		properties.add(property); // older property is overwritten. 
	}

	protected void removeProperty(Property property) throws IllegalArgumentException {
		if(properties.contains(property))
			properties.remove(property);
		else
			throw new IllegalArgumentException("The property with name " + property.getName() + " does not exist.");
	}
	
	

	

	

}
