package com.softdb.kdlog.frame;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import com.softdb.bojava.gui.JBoPanel;
import com.softdb.bojava.gui.JBoTextField;

public abstract class AbstractLogFramePanel extends AbstractFrame
{
    private static final long serialVersionUID = 7902710489854036676L;

    private Boolean req;

    protected ButtonGroup bgRange;
    protected JRadioButton rbId;
    protected JRadioButton rbDates;
    protected JRadioButton rbRequestId;

    protected JBoTextField tfLastId;
    protected JBoTextField tfDateStart;
    protected JBoTextField tfDateEnd;
    protected JBoTextField tfRequestId;

    protected JButton btnReset;
    protected JButton btnCustomWhere;

    public AbstractLogFramePanel(String title, Boolean req)
    {
	super(title);

	this.req = req;

	JPanel panelRange = createPanelRange();
	JPanel panelSelect = createPanelSelect();

	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	
	add(panelRange);
	add(panelSelect);
	add(scrollTable);
    }

    private JPanel createPanelRange()
    {
	rbId = new JRadioButton("ID");
	rbDates = new JRadioButton("Dates");
	rbRequestId = new JRadioButton("RequestID");

	bgRange = new ButtonGroup();
	bgRange.add(rbId);
	bgRange.add(rbDates);
	bgRange.add(rbRequestId);

	tfLastId = new JBoTextField();
	tfDateStart = new JBoTextField();
	tfDateEnd = new JBoTextField();
	tfRequestId = new JBoTextField();

	JBoPanel panel = new JBoPanel("Range");
	setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel.add(new JLabel("Last ID"));
	panel.add(tfLastId);
	panel.add(new JLabel("Date start"));
	panel.add(tfDateStart);
	panel.add(new JLabel("Date end"));
	panel.add(tfDateEnd);
	if (req)
	{
	    panel.add(new JLabel("Request_id"));
	    panel.add(tfRequestId);
	}
	panel.add(rbId);
	panel.add(rbDates);
	if (req)
	    panel.add(rbRequestId);

	return panel;
    }

    private JPanel createPanelSelect()
    {
	btnCustomWhere = new JButton("Custom Where");	
	btnReset = new JButton("Reset");

	JBoPanel panel = new JBoPanel("Select");
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel.add(btnSelect);
	panel.add(btnCustomWhere);
	panel.add(btnCustomColumn);
	panel.add(btnReset);
	panel.add(btnExportCSV);

	return panel;
    }
}
