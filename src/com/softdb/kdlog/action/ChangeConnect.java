package com.softdb.kdlog.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softdb.kdlog.app.DBUtil;
import com.softdb.kdlog.app.MenuApp;
import com.softdb.kdlog.types.Connections;

public class ChangeConnect implements ActionListener
{

    private static final Logger logger = LogManager.getLogger(ChangeConnect.class);

    @Override
    public void actionPerformed(ActionEvent e)
    {
	Connections conn = (Connections) MenuApp.cbConnections.getSelectedItem();

	logger.info(conn.getName() + " connecting...");
	Boolean status = DBUtil.setCurrent(conn);
	MenuApp.statusMenu(status);
	if (status)
	    logger.info(conn.getName() + " connected.");
	else
	{
	    logger.info(conn.getName() + " connecting abort.");
	    MenuApp.cbConnections.setSelectedIndex(-1);
	}
    }

}
