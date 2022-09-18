package com.softdb.kdlog.types;

public class FrameParams
{
    String title;
    Boolean req;
    String ownerTable;
    String tableName;    
    String[] excludeCols;
    String colId;
    String colDate;
    String colSearch;

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	this.title = title;
    }

    public Boolean getReq()
    {
	return req;
    }

    public void setReq(Boolean req)
    {
	this.req = req;
    }

    public String getOwnerTable()
    {
	return ownerTable;
    }

    public void setOwnerTable(String ownerTable)
    {
	this.ownerTable = ownerTable;
    }

    public String getTableName()
    {
	return tableName;
    }

    public void setTableName(String tableName)
    {
	this.tableName = tableName;
    }

    public String[] getExcludeCols()
    {
	return excludeCols;
    }

    public void setExcludeCols(String[] excludeCols)
    {
	this.excludeCols = excludeCols;
    }

    public String getColId()
    {
	return colId;
    }

    public void setColId(String colId)
    {
	this.colId = colId;
    }

    public String getColDate()
    {
	return colDate;
    }

    public void setColDate(String colDate)
    {
	this.colDate = colDate;
    }

    public String getColSearch()
    {
        return colSearch;
    }

    public void setColSearch(String colSearch)
    {
        this.colSearch = colSearch;
    }

}
