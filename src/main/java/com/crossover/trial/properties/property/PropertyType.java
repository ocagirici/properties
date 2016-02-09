package com.crossover.trial.properties.property;

public class PropertyType extends PropertyAttribute {
	
	public PropertyType(String data) {
		setData(data);
		setKnown(true);
	}

	public PropertyType(int unset) { // if an integer is passed, then the type is missing.
		setData("TYPE_UNSET");
		setKnown(false);
	}


}
