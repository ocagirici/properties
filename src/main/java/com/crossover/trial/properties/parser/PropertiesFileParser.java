package com.crossover.trial.properties.parser;

import java.util.ArrayList;

import com.crossover.trial.properties.property.Property;
import com.crossover.trial.properties.property.PropertyName;
import com.crossover.trial.properties.property.PropertyType;
import com.crossover.trial.properties.property.PropertyValue;

public class PropertiesFileParser extends TrialAppFileParser{

	public PropertiesFileParser(PropertiesFile file) {
		this.file = file;
	}

	@Override
	public String[] parseLine(String line) {
		String[] splitted = line.split("=");
		if(splitted.length < 2) return new String[]{"RETURN_PROPERTY_UNSET"}; // if the format is violated, the property is missing.
		else return splitted;
	}


}
