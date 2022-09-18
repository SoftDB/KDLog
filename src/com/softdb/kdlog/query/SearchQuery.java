package com.softdb.kdlog.query;

import com.softdb.kdlog.types.FrameParams;
import com.softdb.kdlog.types.ModelTableQuery;
import com.softdb.kdlog.types.QueryParams;

public class SearchQuery extends AbstractQuery implements IQuery
{
    private FrameParams frameParams;

    public SearchQuery(FrameParams paramsFrame)
    {
	this.frameParams = paramsFrame;
    }

    @Override
    public ModelTableQuery generateQuery(QueryParams params)
    {
	StringBuffer sb = new StringBuffer();
	String c = frameParams.getOwnerTable() + "." + frameParams.getTableName();
	sb.append(bulidSQL(c, params, frameParams.getColSearch()));

	setSQL(sb.toString());

	return execute(sb.toString());
    }

    @Override
    public String getError()
    {
	return err;
    }

}
