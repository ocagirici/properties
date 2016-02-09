package com.crossover.trial.properties.property;

public class Property implements Comparable<Property> {
	
	private PropertyName propertyName;
	private PropertyValue propertyValue;
	private PropertyType propertyType;
	private boolean isKnown;
		
	/** begin constructors **/
	public Property(PropertyName propertyName, PropertyType propertyType, PropertyValue propertyValue)
	{
		setName(propertyName);
		setType(propertyType);
		setValue(propertyValue);
		if(propertyType.isKnown() && propertyValue.isKnown()) // if both value and type are known,
			setKnown(true); // then the property is known.
		else setKnown(false); // else, it is missing.
	}
	
	/** end constructors **/
	
	/** begin getters and setters **/
	public PropertyName getName() {
		return propertyName;
	}

	public void setName(PropertyName propertyName) {
		this.propertyName = propertyName;
	}

	
	public PropertyValue getValue() {
		return propertyValue;
	}

	public void setValue(PropertyValue propertyValue) {
		this.propertyValue = propertyValue;
	}

	
	public PropertyType getType() {
		return propertyType;
	}

	public void setType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	
	/** end getters and setters **/
	


	public String toString()
	{
		return propertyName + ", " + propertyType + ", " + propertyValue;
	}
	
	

	@Override
	public int compareTo(Property that) {
		return this.getName().generic().compareTo(that.getName().generic());
	
	}

	public void clear() {
		setType(null);
		setValue(null);
		
	}

	public boolean isKnown() {
		return isKnown;
	}

	public void setKnown(boolean isKnown) {
		this.isKnown = isKnown;
	}
	
	
	
	
}
