package com.softdb.kdlog.frame;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.softdb.kdlog.app.App;
import com.softdb.kdlog.types.Columns;

public class CustomColumnDialog
{
    private List<Columns> cols;
    private List<Columns> prevCols;

    public CustomColumnDialog(List<Columns> cols)
    {
	this.cols = cols;
	this.prevCols = cols;
	
	ActionListener action = e -> {
	    JCheckBox cb = (JCheckBox) e.getSource();
	    for (Columns col : prevCols)
	    {
		if (col.getName().equals(cb.getText()))
		{
		    col.setVisible(cb.isSelected());
		}
	    }
	};

	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	for (Columns col : cols)
	{
	    JCheckBox cb = new JCheckBox(col.getName());

	    cb.setSelected(col.getVisible());
	    cb.addActionListener(action);
	    panel.add(cb);
	}

	int res = JOptionPane.showConfirmDialog(App.getMainFrame(), panel, "Custom Columns", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (res == 1)
	    cols = prevCols;
    }

    public List<Columns> getCols()
    {
	return cols;
    }
}
