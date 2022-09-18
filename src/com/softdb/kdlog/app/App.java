package com.softdb.kdlog.app;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import com.softdb.kdlog.action.ExitApp;

public class App
{
    private static JFrame frame;
    private static JDesktopPane desktop;

    public static void main(String[] args)
    {
	EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		try
		{
		    App app = new App();
		    App.frame.setVisible(true);
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	});
    }

    private void initialize()
    {
	frame = new JFrame("KDLog");
	frame.setBounds(Config.getSizeMainFrame());
	frame.setJMenuBar(MenuApp.getMenuBar());
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	frame.addWindowListener(new java.awt.event.WindowAdapter()
	{
	    @Override
	    public void windowClosing(java.awt.event.WindowEvent windowEvent)
	    {
		new ExitApp().action();
	    }
	});

	desktop = new JDesktopPane();

	frame.getContentPane().add(MenuApp.getToolBar(), BorderLayout.NORTH);
	frame.getContentPane().add(desktop, BorderLayout.CENTER);
    }

    public App()
    {
	Config.init();
	MenuApp.init();
	initialize();
    }

    public static JFrame getMainFrame()
    {
	return frame;
    }

    public static JDesktopPane getDesktop()
    {
	return desktop;
    }

    public static void setCursor(int cursor)
    {
	frame.getContentPane().setCursor(new Cursor(cursor));
    }
}
