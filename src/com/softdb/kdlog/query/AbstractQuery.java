package com.softdb.kdlog.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softdb.kdlog.app.DBUtil;
import com.softdb.kdlog.types.Columns;
import com.softdb.kdlog.types.ModelTableQuery;
import com.softdb.kdlog.types.QueryParams;

public abstract class AbstractQuery
{
    private String sql;
    protected String err;

    public String getSQL()
    {
	return sql;
    }

    public void setSQL(String sql)
    {
	this.sql = sql;
    }

    public List<Columns> getColumns(String owner, String table)
    {
	String qry = "SELECT column_name,data_type FROM all_tab_cols WHERE owner='" + owner + "' AND table_name='" + table + "' ORDER BY column_id";
	List<Columns> cols = new ArrayList<Columns>();
	try
	{
	    ResultSet rs = DBUtil.dbExecuteQuery(qry);
	    while (rs.next())
	    {
		Columns col = new Columns();
		col.setName(rs.getString("column_name"));
		col.setType(rs.getString("data_type"));
		cols.add(col);
	    }

	    return cols;
	} catch (ClassNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return cols;
    }

    public String bulidSQLColumns(QueryParams params, String tableName)
    {
	StringBuffer sb = new StringBuffer();
	Boolean next = false;

	sb.append("SELECT ");

	for (Columns col : params.getCols())
	{
	    if (col.getVisible())
	    {
		if (next)
		    sb.append(",");
		next = true;

		sb.append(col.getName());
	    }
	}

	sb.append(" FROM " + tableName);

	return sb.toString();
    }

    public String bulidSQL(String tableName, QueryParams params, String fieldId, String fieldDate)
    {
	StringBuffer sb = new StringBuffer();

	sb.append(bulidSQLColumns(params, tableName));
	/*
	 * if (tableName.equals("al.log_afh")) sb.append(
	 * "SELECT log_id,TO_CHAR(log_time, 'YYYY-MM-DD HH24:MI:SSxFF') as log_time,log_text,log_source,log_params,log_statement,log_statement_long "
	 * );
	 */
	if (params.getRange().equals("REQUESTID"))
	    if (tableName.equals("dr.log_procedure_text"))
	    {
		sb.append(" procedure_audit_id in ");
		sb.append("(SELECT lp.procedure_audit_id ");
		sb.append("FROM dr.log_job lj, ");
		sb.append("dr.log_procedure lp ");
		sb.append("WHERE lp.process_id = lj.process_id ");
		sb.append("AND lj.request_id=" + params.getRequestId() + ")");
	    } else
	    {
		sb.append("request_id=" + params.getRequestId());
	    }

	if (params.getRange().equals("LASTID"))
	{
	    sb.append(" WHERE " + fieldId + " BETWEEN (SELECT MAX(" + fieldId + ") - " + params.getLastId() + " FROM " + tableName + ") AND (SELECT max("
		    + fieldId + ") FROM " + tableName + ") ");
	}
	if (params.getRange().equals("DATES"))
	{
	    sb.append(" WHERE " + fieldDate + " BETWEEN ");
	    sb.append("TO_DATE('" + params.getDateStart() + "','dd-mm-yyyy hh24:mi:ss') ");
	    sb.append("AND ");
	    sb.append("TO_DATE('" + params.getDateEnd() + "','dd-mm-yyyy hh24:mi:ss') ");
	}

	if (!params.getCustomSQL().isEmpty())
	    sb.append(" AND " + params.getCustomSQL() + " ");

	sb.append(" ORDER BY " + fieldId + " DESC");

	return sb.toString();
    }

    public String bulidSQL(String tableName, QueryParams params, String fieldSearch)
    {
	StringBuffer sb = new StringBuffer();

	sb.append(bulidSQLColumns(params, tableName));

	if (tableName.equals("all_objects"))
	    sb.append(
		    " WHERE owner NOT IN ( 'REMOTE_SCHEDULER_AGENT', 'ORDPLUGINS', 'OLAPSYS', 'LBACSYS', 'ORDSYS', 'XDB', 'WMSYS', 'SYSTEM', 'SYS', 'GSMADMIN_INTERNAL', "
			    + "'DVSYS', 'DVF', 'DBSNMP', 'DBSFWUSER', 'CTXSYS', 'CI_TOOLS', 'AUDSYS', 'OUTLN', 'ORACLE_OCM', 'MDSYS' ) AND object_type not IN ('JAVA CLASS') ");

	if (tableName.equals("all_source"))
	    sb.append(
		    " WHERE owner NOT IN ( 'REMOTE_SCHEDULER_AGENT', 'ORDPLUGINS', 'OLAPSYS', 'LBACSYS', 'ORDSYS', 'XDB', 'WMSYS', 'SYSTEM', 'SYS', 'GSMADMIN_INTERNAL',\r\n"
			    + "  'DVSYS', 'DVF', 'DBSNMP', 'DBSFWUSER', 'CTXSYS', 'CI_TOOLS', 'AUDSYS', 'OUTLN', 'ORACLE_OCM', 'MDSYS' )");

	if (!params.getSearch().isEmpty())
	{
	    if (tableName.equals("all_objects") || tableName.equals("all_source"))
		sb.append(" AND ");
	    else
		sb.append(" WHERE ");

	    sb.append("UPPER(" + fieldSearch + ") LIKE '%" + params.getSearch().toUpperCase() + "%'");
	}

	if (tableName.equals("all_source"))
	    sb.append(" ORDER BY name,line");
	
	return sb.toString();
    }

    public ModelTableQuery execute(String sql)
    {
	try
	{
	    ResultSet rs = DBUtil.dbExecuteQuery(sql);
	    ModelTableQuery modelTabelQuery = new ModelTableQuery(rs);
	    err = "";
	    
	    return modelTabelQuery;
	} catch (Exception e)
	{
	    err = e.getMessage();
	    e.printStackTrace();
	}

	return null;
    }
}
