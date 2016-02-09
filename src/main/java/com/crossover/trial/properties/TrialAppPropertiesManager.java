package com.crossover.trial.properties;

import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import com.crossover.trial.properties.parser.PropertiesFile;
import com.crossover.trial.properties.parser.TrialAppFileParser;
import com.crossover.trial.properties.property.Property;

/**
 * A simple main method to load and print properties. You should feel free to change this class
 * or to create additional class. You may add additional methods, but must implement the 
 * AppPropertiesManager API contract.
 * 
 * Note: a default constructor is required
 *
 * @author code test administrator
 */
public class TrialAppPropertiesManager implements AppPropertiesManager {
	
	public TrialAppPropertiesManager() {}
	
	@Override
    public AppProperties loadProps(List<String> propUris) {
    	return new TrialAppProperties(propUris);
    }

    @Override
    public void printProperties(AppProperties props, PrintStream sync) {
        sync.println(props);
    }
    
}
