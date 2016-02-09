package com.crossover.trial.properties.property;

public abstract class PropertyAttribute {
	/**
	 * This class provides type safety. Since the constructor of Property takes three String objects,
	 * it is better to ensure the order of these objects.
	 * Moreover, we can encapsulate isKnown boolean.
	 * 
	 */
	protected String data;
	protected boolean isKnown;
	
	public void setData(String data) { 
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public void setKnown(boolean isKnown) {
		this.isKnown = isKnown;
	}
	
	public boolean isKnown() {
		return isKnown;
	}
	
	public String toString() {
		return data;
	}
	
}
