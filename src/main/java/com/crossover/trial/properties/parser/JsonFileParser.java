package com.crossover.trial.properties.parser;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.crossover.trial.properties.property.*;

public class JsonFileParser extends TrialAppFileParser{
	
	public JsonFileParser(PropertiesFile propertiesFile) {
		this.file = propertiesFile;
	}

	@Override
	public String[] parseLine(String line) {
		/**
		 * If line contains one of { or }, that means it is not a property line.
		 * Assumption: values do not contains '{' or '}'
		 * 
		 * note: bad practice. change this.
		 * 
		 * 
		 */
		if(line.contains("{") || line.contains("}")) 
			return null;
		line = line.replace("\"", ""); // remove the forward slashes
		line = line.replace(",", ""); // remove the commas at the end of lines.
		line = line.replace(" ", ""); // remove the whitespaces.
		String[] splitted = line.split(":"); // split the line from ':' to get two parts
		if(splitted.length < 2) 
			return new String[]{"RETURN_PROPERTY_UNSET"};
		if(splitted.length == 2)
			return splitted;
		else
		{
			String[] parsed = new String[2];
			parsed[0] = splitted[0];
			parsed[1] = splitted[1];
			for(int i=2; i<splitted.length; i++)
				parsed[1] += ":" + splitted[i]; // add all splitted parts to the actual part, excluding the prefix.
			return parsed;
		}
		
	}
	
	


	
	
}

