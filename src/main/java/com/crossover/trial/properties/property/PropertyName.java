package com.crossover.trial.properties.property;

public class PropertyName extends PropertyAttribute{
	private String genericFormat; // generic format is lowercase and splitted with dots.
	public PropertyName(String data) {
		setData(data);
		this.genericFormat = data.replace("_", "."); // does not need setter.
		this.genericFormat = genericFormat.toLowerCase();
	}
	
	public String generic() {
		return genericFormat;
	}
	

}
