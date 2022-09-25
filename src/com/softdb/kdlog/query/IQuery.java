package com.softdb.kdlog.query;

import com.softdb.bojava.db.ModelTableQuery;
import com.softdb.kdlog.types.QueryParams;

public interface IQuery
{
    public ModelTableQuery generateQuery(QueryParams params);
}
