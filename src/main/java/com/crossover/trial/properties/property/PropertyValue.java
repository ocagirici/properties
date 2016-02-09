package com.crossover.trial.properties.property;

public class PropertyValue extends PropertyAttribute{

	private String data;
	private boolean isKnown;
	
	public PropertyValue(String data) {
		setData(data);
		setKnown(true);
	}
	
	public PropertyValue(int unset) {  // if an integer is passed, then the value is missing.
		setData("VALUE_UNSET");
		setKnown(false);
	}

}
