package com.softdb.kdlog.query;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softdb.bojava.db.ModelTableQuery;
import com.softdb.kdlog.app.DBUtil;
import com.softdb.kdlog.types.Columns;
import com.softdb.kdlog.types.QueryParams;

public abstract class AbstractQuery
{
    private String sql;

    private static final Logger logger = LogManager.getLogger(AbstractQuery.class);

    public String getSQL()
    {
	return sql;
    }

    public void setSQL(String sql)
    {
	this.sql = sql;
	logger.info(sql);
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
	} catch (Exception e)
	{
	    logger.error(e);
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

	if (params.getRange().equals("REQUESTID"))
	    if (tableName.equals("DR.LOG_PROCEDURE_TEXT"))
	    {
		sb.append(" WHERE procedure_audit_id in ");
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
	    ModelTableQuery modelTabelQuery;
	    ResultSet rs = DBUtil.dbExecuteQuery(sql);
	    if (rs != null)
	    {
		modelTabelQuery = new ModelTableQuery(rs);
		return modelTabelQuery;
	    } else
		return null;
	} catch (Exception exc)
	{
	    return null;
	}
    }
}
