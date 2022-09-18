package com.softdb.kdlog.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.softdb.kdlog.types.Connections;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class DBUtil
{
    private static String current = "";

    // Declare JDBC Driver
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    // Connection
    private static Connection conn = null;
    private static String connStr = "";

    public static void setCurrent(Connections connections)
    {
	current = connections.getName();
	connStr = "jdbc:oracle:thin:" + connections.getUser() + "/" + connections.getPass() + "@" + connections.getHost() + ":" + connections.getPort() + ":"
		+ connections.getSid();
	try
	{
	    dbDisconnect();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try
	{
	    dbConnect();
	} catch (ClassNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public static String getCurrent()
    {
	return current;
    }

    public static void dbConnect() throws SQLException, ClassNotFoundException
    {
	// Setting Oracle JDBC Driver
	try
	{
	    Class.forName(JDBC_DRIVER);
	} catch (ClassNotFoundException e)
	{
	    System.out.println("Where is your Oracle JDBC Driver?");
	    e.printStackTrace();
	    throw e;
	}

	System.out.println("Oracle JDBC Driver Registered!");

	// Establish the Oracle Connection using Connection String
	try
	{
	    conn = DriverManager.getConnection(connStr);
	} catch (SQLException e)
	{
	    System.out.println("Connection Failed! Check output console" + e);
	    e.printStackTrace();
	    // throw e;
	    Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
	    alert.showAndWait();

	}
    }

    // Close Connection
    public static void dbDisconnect() throws SQLException
    {
	try
	{
	    if (conn != null && !conn.isClosed())
	    {
		conn.close();
	    }
	} catch (Exception e)
	{
	    // throw e;
	}
    }

    // DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException
    {
	if (!conn.isValid(5))
	    dbConnect();

	Statement stmt = null;
	ResultSet resultSet = null;
	System.out.println("Select statement: " + queryStmt + "\n");
	stmt = conn.createStatement();
	resultSet = stmt.executeQuery(queryStmt);
	
	return resultSet;
    }

    // DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException
    {
	// Declare statement as null
	Statement stmt = null;
	try
	{
	    // Connect to DB (Establish Oracle Connection)
	    dbConnect();
	    // Create Statement
	    stmt = conn.createStatement();
	    // Run executeUpdate operation with given sql statement
	    stmt.executeUpdate(sqlStmt);
	} catch (SQLException e)
	{
	    System.out.println("Problem occurred at executeUpdate operation : " + e);
	    throw e;
	} finally
	{
	    if (stmt != null)
	    {
		// Close statement
		stmt.close();
	    }
	    // Close connection
	    dbDisconnect();
	}
    }
}
