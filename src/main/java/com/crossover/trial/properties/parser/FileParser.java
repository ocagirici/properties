package com.crossover.trial.properties.parser;

import com.crossover.trial.properties.property.*;

public interface FileParser {
	/**
	 * Does the parsing and returns the parsed properties file.
	 * @return Parsed PropertiesFile object 
	 */
	PropertiesFile parse();
	
	/**
	 * Parses a line read from a file.
	 * @param line
	 * @return parsed line as an array of strings
	 */
	String[] parseLine(String line);
	
	/**
	 * Determines the name of the property given as a parsed line
	 * @param parsedLine
	 * @return name of the property
	 */
	PropertyName determineName(String[] parsedLine);
	
	/**
	 * Determines the type of the property given its name and value
	 * @param parsedLine
	 * @return name of the property
	 */
	PropertyType determineType(PropertyName name, PropertyValue value);
	
	/**
	 * Determines the value of the property given as a parsed line
	 * @param parsedLine
	 * @return name of the property
	 */
	PropertyValue determineValue(String[] parsedLine);

}
