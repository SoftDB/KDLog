package com.softdb.kdlog.app;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public abstract class MenuAbstract
{
    protected static Hashtable<Integer, JInternalFrame> htFrames;
    protected static Integer countFrames = 0;

    protected static JMenuBar menuBar;
    protected static JToolBar toolBar;

    public static void create()
    {
	htFrames = new Hashtable<Integer, JInternalFrame>();

	menuBar = new JMenuBar();

	toolBar = new JToolBar();
	toolBar.setFloatable(false);
	toolBar.setMargin(new Insets(6, 5, 6, 5));
    }

    public static JMenuBar getMenuBar()
    {
	return menuBar;
    }

    public static JToolBar getToolBar()
    {
	return toolBar;
    }

    protected static JMenuItem create(String name, char mneomonic, KeyStroke ks, ActionListener al)
    {
	JMenuItem item = new JMenuItem(name);
	item.setMnemonic(mneomonic);
	item.addActionListener(al);
	item.setAccelerator(ks);

	return item;
    }

    protected static JMenuItem create(String name, char mneomonic, ActionListener al)
    {
	JMenuItem item = new JMenuItem(name);
	item.setMnemonic(mneomonic);
	item.addActionListener(al);

	return item;
    }

    public static void addFrame(JInternalFrame frame)
    {
	htFrames.put(countFrames, frame);
	++countFrames;
    }

    public static Integer[] getPosXYFrame()
    {
	if (countFrames == 0)
	    return new Integer[] { 10, 10 };
	else
	{
	    JInternalFrame frame = htFrames.get(countFrames - 1);
	    return new Integer[] { frame.getX() + 25, frame.getY() + 25 };
	}
    }
}
