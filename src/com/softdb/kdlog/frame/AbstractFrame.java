package com.softdb.kdlog.frame;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.softdb.bojava.gui.JBoScrollPane;
import com.softdb.kdlog.app.Config;
import com.softdb.kdlog.app.MenuApp;
import com.softdb.kdlog.types.Columns;
import com.softdb.kdlog.types.ColumnsAutoSizer;

public abstract class AbstractFrame extends JInternalFrame
{
    protected JButton btnSelect;
    protected JButton btnCustomColumn;
    protected JButton btnExportCSV;

    protected JBoScrollPane scrollTable;
    protected JTable tableData;
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

	tableData = new JTable();

	scrollTable = new JBoScrollPane(tableData, "Result", 1900, 700);
    }

    private void createEvent()
    {
	btnExportCSV.addActionListener(new ActionListener()
	{

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		JFrame parentFrame = new JFrame();
		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION)
		{
		    File fileToSave = fileChooser.getSelectedFile();
		    {

			try
			{
			    TableModel model = tableData.getModel();
			    FileWriter csv = new FileWriter(fileToSave);

			    for (int i = 0; i < model.getColumnCount(); i++)
			    {
				if (i > 0)
				    csv.write(",");
				csv.write(model.getColumnName(i));
			    }

			    csv.write("\r");

			    for (int i = 0; i < model.getRowCount(); i++)
			    {
				for (int j = 0; j < model.getColumnCount(); j++)
				{
				    try
				    {
					if (j > 0)
					    csv.write(",");
					csv.write(model.getValueAt(i, j).toString().trim().replace("\r", "").replace("\n", ""));
				    } catch (Exception ee)
				    {
					if (j > 0)
					    csv.write(",");
					csv.write("");
				    }
				}
				csv.write("\n");
			    }

			    csv.close();

			} catch (Exception exc)
			{
			    exc.printStackTrace();
			}

		    }
		}
	    }
	});

	tableData.addMouseListener(new MouseAdapter()
	{
	    public void mouseClicked(MouseEvent e)
	    {
		if (e.getClickCount() == 2)
		{
		    String cellValue = tableData.getModel().getValueAt(tableData.getSelectedRow(), tableData.getSelectedColumn()).toString();
		    JOptionPane.showMessageDialog(new JFrame(), cellValue);
		}
	    }
	});

	tableData.getActionMap().put("copy", new AbstractAction()
	{
	    private static final long serialVersionUID = 7074493484383118707L;

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		String cellValue = tableData.getModel().getValueAt(tableData.getSelectedRow(), tableData.getSelectedColumn()).toString();
		StringSelection stringSelection = new StringSelection(cellValue);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
	    }
	});

	btnCustomColumn.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		CustomColumnDialog dialog = new CustomColumnDialog(columnTable);
		columnTable = dialog.getCols();
	    }

	});

	tableData.getModel().addTableModelListener(new TableModelListener()
	{
	    public void tableChanged(TableModelEvent e)
	    {
		ColumnsAutoSizer.sizeColumnsToFit(tableData);
	    }
	});

    }

}
