package com.softdb.kdlog.frame;

import com.softdb.kdlog.query.IQuery;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.softdb.bojava.db.ModelTableQuery;
import com.softdb.bojava.gui.JBoPanel;
import com.softdb.bojava.gui.JBoTextField;
import com.softdb.kdlog.types.QueryParams;

public abstract class AbstractSearchFrame extends AbstractFrame
{

    private static final long serialVersionUID = -311319565952937595L;
    
    protected JBoTextField tfSearch;

    public AbstractSearchFrame(String title)
    {
	super(title);
	JPanel panelSearch = createPanelSearch();
	JPanel panelSelect = createPanelSelect();

	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

	add(panelSearch);
	add(panelSelect);
	add(scrollTable);
    }

    protected void generateModel(IQuery query)
    {
	QueryParams params = generateParams();
	ModelTableQuery model = query.generateQuery(params);

	if (model != null)
	    tableData.setModel(model);
    }

    private QueryParams generateParams()
    {
	QueryParams params = new QueryParams();

	params.setSearch(tfSearch.getText());
	params.setCols(columnTable);

	return params;
    }

    private JPanel createPanelSearch()
    {
	tfSearch = new JBoTextField();

	JBoPanel panel = new JBoPanel("Search");
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel.add(new JLabel("Search"));
	panel.add(tfSearch);

	return panel;
    }

    private JPanel createPanelSelect()
    {
	JBoPanel panel = new JBoPanel("Select");
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel.add(btnSelect);
	panel.add(btnCustomColumn);
	panel.add(btnExportCSV);

	return panel;
    }

}