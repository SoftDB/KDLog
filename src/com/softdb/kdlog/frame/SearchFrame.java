package com.softdb.kdlog.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.softdb.kdlog.query.SearchQuery;
import com.softdb.kdlog.types.FrameParams;

public class SearchFrame extends AbstractSearchFrame
{
    private static final long serialVersionUID = 3990864091525368099L;

    private SearchQuery query;

    public SearchFrame(FrameParams frameParams)
    {
	super(frameParams.getTitle());
	query = new SearchQuery(frameParams);
	columnTable = query.getColumns(frameParams.getOwnerTable(), frameParams.getTableName());

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
