package com.softdb.kdlog.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.ScrollPaneConstants;

import com.softdb.bojava.gui.JBoScrollPane;
import com.softdb.bojava.gui.JBoTable;
import com.softdb.bojava.gui.JBoTableActionCSV;
import com.softdb.kdlog.app.Config;
import com.softdb.kdlog.app.MenuApp;
import com.softdb.kdlog.types.Columns;

public abstract class AbstractFrame extends JInternalFrame
{
    protected JButton btnSelect;
    protected JButton btnCustomColumn;
    protected JButton btnExportCSV;

    protected JBoScrollPane scrollTable;
    protected JBoTable tableData;
    protected List<Columns> columnTable;

    private static final long serialVersionUID = -3866548328552509195L;

    public AbstractFrame(String title)
    {
	setTitle(title);
	setResizable(true);
	setMaximizable(true);
	setIconifiable(true);
	setClosable(true);

	Integer[] posxy = MenuApp.getPosXYFrame();
	setBounds(posxy[0], posxy[1], Config.getWidthFrame(), Config.getHeightFrame());

	createObject();
	createEvent();

	MenuApp.addFrame(this);
    }

    private void createObject()
    {
	btnSelect = new JButton("Select");
	btnCustomColumn = new JButton("Custom Columns");
	btnExportCSV = new JButton("Save to CSV");

	tableData = new JBoTable();

	scrollTable = new JBoScrollPane(tableData, "Result", 1900, 700);
	scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    private void createEvent()
    {
	btnExportCSV.addActionListener(new JBoTableActionCSV(tableData));

	btnCustomColumn.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		CustomColumnDialog dialog = new CustomColumnDialog(columnTable);
		columnTable = dialog.getCols();
	    }

	});

    }

}
