package com.softdb.kdlog.frame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.softdb.bojava.gui.JBoComboBox;
import com.softdb.bojava.gui.JBoPanel;
import com.softdb.bojava.gui.JBoTextField;
import com.softdb.kdlog.app.App;
import com.softdb.kdlog.types.Columns;

public class CustomWhereDialog
{
    private String[] operatorsStr = { "=", "LIKE%", "%LIKE", "%LIKE%" };
    private String[] operatorsNum = { "=", ">", ">=", "<", "<=", "<>" };

    private JTextArea taWhere;
    private JBoComboBox<String[]> cbFields;
    private JBoComboBox<String[]> cbOperators;
    private JBoTextField tfValue;
    private JButton btnAdd;
    private JButton btnClear;
    private JCheckBox cbManual;

    private List<Columns> cols;
    private String[] excludeCols;
    private String prevWhere = "";

    public CustomWhereDialog(List<Columns> cols, String[] excludeCols, String where)
    {
	this.cols = cols;
	this.excludeCols = excludeCols;
	prevWhere = where;
	
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	JPanel panelWhere = createPanelWhere();
	JPanel panelDefine = createPanelDefine();
	panel.add(panelWhere);
	panel.add(panelDefine);

	fill();
	taWhere.setText(where);

	eventWhere();

	int res = JOptionPane.showConfirmDialog(App.getMainFrame(), panel, "Custom Where", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	if (res == 1)
	    taWhere.setText(prevWhere);
    }

    public String getWhere()
    {
	return taWhere.getText();
    }

    private JPanel createPanelWhere()
    {
	taWhere = new JTextArea(2, 100);
	taWhere.setEnabled(false);

	JBoPanel panel = new JBoPanel("WHERE clause");

	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel.add(taWhere);

	return panel;
    }

    private JPanel createPanelDefine()
    {
	cbFields = new JBoComboBox<String[]>(new String[] {});
	cbOperators = new JBoComboBox<String[]>(new String[] {});

	tfValue = new JBoTextField();
	cbManual = new JCheckBox("manual WHERE clause");
	btnAdd = new JButton("Add");
	btnClear = new JButton("Clear");

	JBoPanel panel = new JBoPanel("Define");
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel.add(new JLabel("Fields"));
	panel.add(cbFields);
	panel.add(new JLabel("Operators"));
	panel.add(cbOperators);
	panel.add(new JLabel("Value"));
	panel.add(tfValue);
	panel.add(btnAdd);
	panel.add(btnClear);
	panel.add(cbManual);

	return panel;
    }

    private void eventWhere()
    {
	cbFields.addActionListener(new ActionListener()
	{

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		DefaultComboBoxModel modelOper;
		Columns col = (Columns) cbFields.getSelectedItem();

		if (col.getType().equals("VARCHAR2"))
		    modelOper = new DefaultComboBoxModel(operatorsStr);
		else
		    modelOper = new DefaultComboBoxModel(operatorsNum);

		cbOperators.setModel(modelOper);
		cbOperators.setSelectedItem(-1);
	    }
	});

	btnAdd.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		Columns col = (Columns) cbFields.getSelectedItem();
		String c1 = col.getName();
		String c2 = (String) cbOperators.getSelectedItem();
		String c3 = tfValue.getText();
		if (!c1.isEmpty() && !c2.isEmpty() && !c3.isEmpty())
		{
		    String w = taWhere.getText();
		    String str = "";
		    if (col.getType().equals("VARCHAR2") || col.getType().equals("CHAR"))
			str = "\'";

		    if (taWhere.getText().length() > 0)
			w = w + " AND ";

		    if (c2.equals("LIKE%"))
			taWhere.setText(w + " " + c1 + " LIKE '" + c3 + "%' ");
		    else if (c2.equals("%LIKE"))
			taWhere.setText(w + " " + c1 + " LIKE '%" + c3 + "' ");
		    else if (c2.equals("%LIKE%"))
			taWhere.setText(w + " " + c1 + " LIKE '%" + c3 + "%' ");
		    else
			taWhere.setText(w + " " + c1 + c2 + str + c3 + str + " ");
		}

	    }
	});

	cbManual.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		if (cbManual.isSelected())
		    taWhere.setEnabled(true);
		else
		    taWhere.setEnabled(false);
	    }
	});

	btnClear.addActionListener(new ActionListener()
	{

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		taWhere.setText("");
	    }
	});

    }

    private void fill()
    {
	List<Columns> colWhere = new ArrayList<Columns>();
	for (Columns col : cols)
	{
	    Boolean addCol = true;
	    for (int i = 0; i < excludeCols.length; ++i)
	    {
		if (col.getName().equals(excludeCols[i]))
		{
		    addCol = false;
		    break;
		}
	    }
	    if (addCol)
		colWhere.add(col);
	}

	DefaultComboBoxModel<Columns> modelFields = new DefaultComboBoxModel<Columns>(new Vector<Columns>(colWhere));
	cbFields.setModel(modelFields);
	cbFields.setSelectedIndex(-1);
    }

}
