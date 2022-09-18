package com.softdb.kdlog.types;

public class Columns
{
    private String name;
    private String type;
    private Boolean visible;

    public Columns()
    {
	visible = true;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getType()
    {
	return type;
    }

    public void setType(String type)
    {
	this.type = type;
    }

    public Boolean getVisible()
    {
        return visible;
    }

    public void setVisible(Boolean visible)
    {
        this.visible = visible;
    }

    public String toString()
    {
	return name;
    }
}
