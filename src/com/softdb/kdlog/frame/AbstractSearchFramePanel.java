package com.softdb.kdlog.frame;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.softdb.bojava.gui.JBoPanel;
import com.softdb.bojava.gui.JBoTextField;

public abstract class AbstractSearchFramePanel extends AbstractFrame
{
    private static final long serialVersionUID = -5468831047505529670L;

    protected JBoTextField tfSearch;

    public AbstractSearchFramePanel(String title)
    {
	super(title);

	JPanel panelSearch = createPanelSearch();
	JPanel panelSelect = createPanelSelect();

	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

	add(panelSearch);
	add(panelSelect);
	add(scrollTable);
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
