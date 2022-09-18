package com.softdb.kdlog.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.softdb.kdlog.query.LogQuery;
import com.softdb.kdlog.types.FrameParams;

public class LogFrame extends AbstractLogFrame
{

    private static final long serialVersionUID = -6762759692266151234L;
    private LogQuery query;

    public LogFrame(FrameParams frameParams)
    {
	super(frameParams.getTitle(), frameParams.getReq());
	
	query = new LogQuery(frameParams);
	columnTable = query.getColumns(frameParams.getOwnerTable(), frameParams.getTableName());
	excludeCols = frameParams.getExcludeCols();

	btnSelect.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		generateModel(query);
	    }
	});
    }
}
