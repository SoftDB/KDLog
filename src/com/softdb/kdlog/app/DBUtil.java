package com.softdb.kdlog.app;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softdb.bojava.db.Connect;
import com.softdb.kdlog.types.Connections;

public class DBUtil
{
    private static Connect conn = null;
    private static List<String> historySQL = new ArrayList<String>();
    private static final Logger logger = LogManager.getLogger(DBUtil.class);

    public static Boolean setCurrent(Connections connection)
    {
	conn = new Connect(connection.getHost(), connection.getPort(), connection.getSid(), connection.getUser(), connection.getPass());

	if (!conn.open())
	{
	    JOptionPane.showMessageDialog(new JFrame(), conn.getLastError());
	    return false;
	} else
	    return true;
    }

    public static ResultSet dbExecuteQuery(String query)
    {
	open();

	try
	{
	    ResultSet resultSet = conn.executeSelect(query);
	    @SuppressWarnings("unused")
	    ResultSetMetaData rsmd = resultSet.getMetaData();
	    historySQL.add(query);

	    return resultSet;
	} catch (Exception exc)
	{
	    logger.error(conn.getLastError());
	    JOptionPane.showMessageDialog(new JFrame(), conn.getLastError());

	    return null;
	}
    }

    private static void open()
    {
	conn.close();
	if (!conn.open())
	{
	    logger.error(conn.getLastError());
	    JOptionPane.showMessageDialog(new JFrame(), conn.getLastError());
	}
    }
}
