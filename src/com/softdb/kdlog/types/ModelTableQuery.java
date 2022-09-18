package com.softdb.kdlog.types;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelTableQuery extends AbstractTableModel
{
    private static final long serialVersionUID = 8331280731439845488L;

    private ArrayList<Object> data;
    private ArrayList<String> colNames;
    private int countCols;

    public ModelTableQuery(ResultSet res) throws SQLException
    {
	colNames = new ArrayList<String>();
	ResultSetMetaData rsmd = res.getMetaData();

	for (int i = 0; i < rsmd.getColumnCount(); i++)
	{
	    String name = rsmd.getColumnName(i + 1);
	    colNames.add(name);
	}

	countCols = rsmd.getColumnCount();

	data = new ArrayList<Object>();
	while (res.next())
	{
	    Object[] row = new Object[countCols];

	    for (int i = 0; i < row.length; i++)
	    {
		row[i] = res.getObject(i + 1);
	    }

	    data.add(row);
	}
    }

    public ArrayList<Object> getData()
    {
	return data;
    }

    public void setData(ArrayList<Object> data)
    {
	this.data = data;
    }

    @Override
    public String getColumnName(int col)
    {
	return (String) colNames.get(col);
    }

    @Override
    public int getColumnCount()
    {
	return countCols;
    }

    @Override
    public int getRowCount()
    {
	return data.size();
    }

    @Override
    public Object getValueAt(int row, int col)
    {
	return ((Object[]) data.get(row))[col];
    }

    public int getColName(String name)
    {
	for (int i = 0; i < countCols; i++)
	{
	    if (colNames.get(i).equals(name))
		return i;
	}

	return -1;
    }
}
