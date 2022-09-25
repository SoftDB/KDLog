package com.softdb.kdlog.app;

import java.awt.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Config
{
    private static Rectangle recMainFrame;

    private static int widthFrame;
    private static int heightFrame;
    private static String pathHome;

    private static final Logger logger = LogManager.getLogger(Config.class);
    
    public static void init()
    {
	Configurator.initialize(null, "log4j2.xml");
	
	recMainFrame = new Rectangle(100, 100, 1280, 720);

	widthFrame = 1100;
	heightFrame = 550;
	pathHome = "C:/";
    }

    public static Rectangle getSizeMainFrame()
    {
	return recMainFrame;
    }

    public static int getWidthFrame()
    {
	return widthFrame;
    }

    public static int getHeightFrame()
    {
	return heightFrame;
    }

    public static void setPathHome(String path)
    {
	pathHome = path;
    }

    public static String getPathHome()
    {
	return pathHome;
    }

}
