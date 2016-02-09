package com.crossover.trial.properties.parser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import com.crossover.trial.properties.property.*;

public abstract class TrialAppFileParser implements FileParser {

	protected PropertiesFile file; // property file to processd

	public Property getProperty(String name, String type, String value) { // default constructor.
		return new Property(new PropertyName(name), new PropertyType(type), new PropertyValue(value));
	}

	public static FileParser createFileParser(String filePath) throws 
	IllegalArgumentException, MalformedURLException, IOException {
		PropertiesFile propertiesFile = null;
		ArrayList<String> lines = new ArrayList<String>();
		Scanner scanner = null;
		try {
			propertiesFile = new PropertiesFile(filePath);
			if(propertiesFile.isURL()) { // if the file is an URL,
				URL url = new URL(filePath); // create a URL.
				scanner = new Scanner(url.openStream());
			}
			else { // otherwise, create a URI
				URI uri = new URI(filePath);
				File f = null;
				try {
					f = new File(uri);
				} catch (Exception e) {
					String[] str = uri.toString().split(":"); // split the file path from ":"
					if(str.length < 2) // if the file path does not meet standard URI syntax
						throw new URISyntaxException("URI Syntax error", "a"); 
					uri = new URI("file:///" + str[1]); // convert URI to absolute path.
					f = new File(uri); // open file.
				}
				File file = new File(uri);
				scanner = new Scanner(file);
			}
			while(scanner.hasNextLine())
				lines.add(scanner.nextLine());
			scanner.close();
			propertiesFile.setLines(lines);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(propertiesFile.getExtension().compareTo("json") == 0)
			return new JsonFileParser(propertiesFile);
		else
			return new PropertiesFileParser(propertiesFile);
	}
	
	public PropertyName determineName(String[] parsedLine) {
		return new PropertyName(parsedLine[0]);
	}


	public PropertyValue determineValue(String[] parsedLine) {
		return new PropertyValue(parsedLine[1]);
	}
	
	public PropertyType determineType(PropertyName propertyName, PropertyValue propertyValue) {
		String name = propertyName.toString().toLowerCase();
		String value = propertyValue.toString().toLowerCase();
		if(name.contains("username") || name.contains("password"))
			return new PropertyType("java.lang.String");
		if(name.contains("timeout"))
			return new PropertyType("java.lang.Integer");
		if(name.contains("region") || 
				value.contains("east") || value.contains("west") || value.contains("central")) 
			return new PropertyType("com.amazonaws.regions.Regions"); 
		/**
		 * The variable is a region with a high probability if it contains "east", "west" or "central.
		 * Although, we can determine from the name.
		 * Might work improperly if a username contains one of the words.
		 * However, we check the username and password before.
		 */
		
		if(value.compareToIgnoreCase("true") == 0 || value.compareToIgnoreCase("false") == 0)
			return new PropertyType("java.lang.Boolean"); // if the value is true or false, then it is a boolean.
		if(value.length() - value.replace("/", "").length() >= 2) // if there exists more than two forward slashes,
			return new PropertyType("java.net.uri"); // then with a great probability, it is a URI.
		
		String[] isNumber = value.split("\\."); // split the string from decimal point, if the value is double
		if(isNumber.length == 2) { // if it was splitted into two
			if(isNumeric(isNumber[0]) && isNumeric(isNumber[1])) // check each part.
				return new PropertyType("java.lang.Double"); // if both parts are numeric, then the type is Double.
		}
		else if(isNumber.length == 1) // if the string does not contain any decimal points
			if(isNumeric(isNumber[0])) // and it is numberic
				return new PropertyType("java.lang.Integer"); // then, it is an integer.
		
		if(value.matches("^[a-zA-Z0-9]*$")) // if the string contains only alphanumeric characters
			return new PropertyType("java.lang.String"); // then it is a string.
		
		return new PropertyType(-1);
	}
	
	public PropertiesFile parse() {
		for(String line : this.file.getLines()) {
			String[] parsedLine = parseLine(line);
			if(parsedLine != null)
			{
				if(parsedLine[0].compareTo("RETURN_PROPERTY_UNSET") == 0)
				{
					PropertyName name = determineName(parsedLine);
					PropertyValue value = new PropertyValue(-1); // set value to unknown
					PropertyType type = new PropertyType(-1); // set type to unknown.
					file.addProperty(new Property (name, type, value));

				}
				PropertyName name = determineName(parsedLine);
				PropertyValue value = determineValue(parsedLine);
				PropertyType type = determineType(name, value);
				file.addProperty(new Property (name, type, value));
			}
			
		}
		return file;
	}

	public PropertiesFile getFile()
	{
		return this.file;
	}

	public boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}  

}
