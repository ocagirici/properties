package com.crossover.trial.properties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.crossover.trial.properties.parser.PropertiesFile;
import com.crossover.trial.properties.parser.TrialAppFileParser;
import com.crossover.trial.properties.property.Property;
import com.crossover.trial.properties.property.PropertyName;

/**
 * A dummy implementation of TrialAppProperties, this clearly doesn't work. Candidates SHOULD change 
 * this class to add their implementation. You are also free to create additional classes
 *
 * note: a default constructor is required.
 *
 * @author code test administrator
 */
public class TrialAppProperties implements AppProperties {

	private TreeMap<String, Property> knownProperties = new TreeMap<String, Property>(); // Provides alphabetical ordering
	private TreeMap<String, Property> missingProperties = new TreeMap<String, Property>(); // Provides alphabetical ordering
	private HashMap<String, Property> allProperties = new HashMap<String, Property>(); // Provides constant running time for get()
	private ArrayList<PropertiesFile> files = new ArrayList<PropertiesFile>();
	private boolean isValid;
	
	public TrialAppProperties()
	{
		knownProperties = new TreeMap<String, Property>();
		missingProperties = new TreeMap<String, Property>();
		allProperties = new HashMap<String, Property>(); 
		files = new ArrayList<PropertiesFile>();
		isValid = false;
	}
	
	public TrialAppProperties(List<String> propUris) {
		try {
			for(String uri: propUris)
				files.add(TrialAppFileParser.createFileParser(uri).parse());
		} catch (Exception e) {
			isValid = false;
			e.printStackTrace();
		}
		
		for(PropertiesFile file : files) // iterate on all files
			for(Property property : file.getProperties()) // get properties from file
				if(property.isKnown()) // if the property is known
					addKnownProperty(property); // add it to the known properties
				else
					addMissingProperty(property); // else, add it to the unknown properties.
		isValid = true;
		
	}
	/**
	 * Adds the given property to knownProperties as well as allProperties.
	 * @param property
	 */
	public void addKnownProperty(Property property) {
		knownProperties.put(property.getName().toString(), property);
		allProperties.put(property.getName().generic(), property); // put the generic name into map
	}
	
	/**
	 * Adds the given property to missingProperties as well as allProperties.
	 * @param property
	 */
	
	public void addMissingProperty(Property property) {
		missingProperties.put(property.getName().toString(), property);
		allProperties.put(property.getName().generic(), property);
	}
	
	

    @Override
    public List<String> getMissingProperties() {
    	ArrayList<String> missingList = new ArrayList<>(missingProperties.keySet());
    	return missingList;
    }

    @Override
    public List<String> getKnownProperties() {
    	ArrayList<String> knownList = new ArrayList<>(knownProperties.keySet());
    	return knownList;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public void clear() {
    	for(Property property : knownProperties.values())
    		property.clear();
    	for(Property property : missingProperties.values())
    		property.clear();

    }

    @Override
    public Property get(String key) {
    	String propName = key.replace("_", ".").toLowerCase(); // convert to generic format.
    	return allProperties.get(propName);
    	   
        
    }
    
    /**
     * Removes a property. If property does not exist, then returns false. If properly removed, returns true.
     * @param key
     * @return true if the property with given name removed succesfully, false otherwise.
     */
    
    public boolean remove(String key) {
    	String propName = key.replace("_", ".").toLowerCase(); // convert to generic format.
    	if(allProperties.containsKey(propName))
    	{
    		Property property = allProperties.get(propName);
    		if(property.isKnown())
    			knownProperties.remove(propName);
    		else
    			missingProperties.remove(propName);
    		allProperties.remove(propName);
    		return true;
    	}
    	
    	return false;
    	
    		
    }
    
    public String toString() {
    	String st = "";
    	for(Property property : knownProperties.values())
    		st += property.toString() + "\n";
    	return st;
    }
}

