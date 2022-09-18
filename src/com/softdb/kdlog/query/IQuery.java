package com.softdb.kdlog.query;

import com.softdb.kdlog.types.ModelTableQuery;
import com.softdb.kdlog.types.QueryParams;

public interface IQuery
{
    public ModelTableQuery generateQuery(QueryParams params);
    public String getError();
}
