package com.softdb.kdlog.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

import java.net.URL;

public class LoadResources
{
    public ImageIcon getIcon(String iconName)
    {
	URL url = getClass().getClassLoader().getResource("resources/icons/" + iconName);

	return new ImageIcon(url, iconName);
    }

    public String getText(String fileName)
    {
	InputStream in = getClass().getClassLoader().getResourceAsStream("resources/texts/" + fileName);
	BufferedReader reader = new BufferedReader(new InputStreamReader(in));

	String line = null;
	StringBuilder stringBuilder = new StringBuilder();
	String ls = System.getProperty("line.separator");

	try
	{
	    try
	    {
		while ((line = reader.readLine()) != null)
		{
		    stringBuilder.append(line);
		    stringBuilder.append(ls);
		}
	    } catch (IOException e)
	    {
		e.printStackTrace();
	    }

	    return stringBuilder.toString();
	} finally
	{
	    try
	    {
		reader.close();
	    } catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
    }
}
