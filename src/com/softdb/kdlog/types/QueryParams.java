package com.softdb.kdlog.types;

import java.util.ArrayList;
import java.util.List;

public class QueryParams
{
    private String range;
    private Integer lastId;
    private String dateStart;
    private String dateEnd;
    private String requestId;
    private String customSQL;
    private String search;
    private String order;

    private List<Columns> cols;

    public QueryParams()
    {
	cols = new ArrayList<Columns>();
    }

    public String getRange()
    {
	return range;
    }

    public void setRange(String range)
    {
	this.range = range;
    }

    public Integer getLastId()
    {
	return lastId;
    }

    public void setLastId(Integer lastId)
    {
	this.lastId = lastId;
    }

    public String getDateStart()
    {
	return dateStart;
    }

    public void setDateStart(String dateStart)
    {
	this.dateStart = dateStart;
    }

    public String getDateEnd()
    {
	return dateEnd;
    }

    public void setDateEnd(String dateEnd)
    {
	this.dateEnd = dateEnd;
    }

    public String getRequestId()
    {
	return requestId;
    }

    public void setRequestId(String requestId)
    {
	this.requestId = requestId;
    }

    public String getCustomSQL()
    {
	return customSQL;
    }

    public void setCustomSQL(String customSQL)
    {
	this.customSQL = customSQL;
    }

    public List<Columns> getCols()
    {
	return cols;
    }

    public String getSearch()
    {
	return search;
    }

    public void setSearch(String search)
    {
	this.search = search;
    }

    public String getOrder()
    {
	return order;
    }

    public void setOrder(String order)
    {
	this.order = order;
    }

    public void setCols(List<Columns> cols)
    {
	this.cols = cols;
    }

}
