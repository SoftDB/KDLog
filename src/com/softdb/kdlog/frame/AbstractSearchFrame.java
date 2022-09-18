package com.softdb.kdlog.frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.softdb.kdlog.query.IQuery;
import com.softdb.kdlog.types.ModelTableQuery;
import com.softdb.kdlog.types.QueryParams;

public abstract class AbstractSearchFrame extends AbstractSearchFrameEvent
{

    private static final long serialVersionUID = -311319565952937595L;

    public AbstractSearchFrame(String title)
    {
	super(title);
    }

    protected void generateModel(IQuery query)
    {
	QueryParams params = generateParams();
	ModelTableQuery model = query.generateQuery(params);
	if (model != null)
	    tableData.setModel(model);
	else
	    JOptionPane.showMessageDialog(new JFrame(), query.getError());
    }

    private QueryParams generateParams()
    {
	QueryParams params = new QueryParams();
	
	params.setSearch(tfSearch.getText());
	params.setCols(columnTable);

	return params;
    }

}
