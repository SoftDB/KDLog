package com.softdb.kdlog.frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.softdb.kdlog.query.IQuery;
import com.softdb.kdlog.types.ModelTableQuery;
import com.softdb.kdlog.types.QueryParams;

public abstract class AbstractLogFrame extends AbstractLogFrameEvent
{
    private static final long serialVersionUID = -6275659515591236620L;

    public AbstractLogFrame(String title, Boolean req)
    {
	super(title, req);
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

	if (tfLastId.isEnabled())
	    params.setRange("LASTID");
	if (tfDateStart.isEnabled())
	    params.setRange("DATES");
	if (tfRequestId.isEnabled())
	    params.setRange("REQUESTID");

	params.setLastId(Integer.valueOf(tfLastId.getText()));
	params.setDateStart(tfDateStart.getText());
	params.setDateEnd(tfDateEnd.getText());
	params.setRequestId(tfRequestId.getText());
	params.setCustomSQL(where);
	params.setCols(columnTable);
	
	return params;
    }

}
