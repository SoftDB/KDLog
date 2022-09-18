package com.softdb.kdlog.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.softdb.kdlog.app.App;


public class ExitApp implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
	action();	
    }
    
    public void action()
    {
	int answer = JOptionPane.showConfirmDialog(App.getMainFrame(), "Are you sure to close this application?", "Exit application?", JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE);
	if (answer == JOptionPane.YES_OPTION)
	{
	    App.getMainFrame().dispose();
	}
    }    
}
