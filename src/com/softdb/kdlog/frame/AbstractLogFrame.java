package com.softdb.kdlog.frame;

import com.softdb.kdlog.query.IQuery;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.softdb.bojava.db.ModelTableQuery;
import com.softdb.bojava.gui.JBoPanel;
import com.softdb.bojava.gui.JBoTextField;
import com.softdb.kdlog.types.QueryParams;

public abstract class AbstractLogFrame extends AbstractFrame
{
    private static final long serialVersionUID = -6275659515591236620L;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private String dateStart;
    private String dateEnd;

    protected String[] excludeCols;
    protected String where = "";
    
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

    public AbstractLogFrame(String title, Boolean req)
    {
	super(title);

	this.req = req;

	JPanel panelRange = createPanelRange();
	JPanel panelSelect = createPanelSelect();

	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	
	add(panelRange);
	add(panelSelect);
	add(scrollTable);
	
	eventRange();
	eventWhere();
	reset();
    }

    protected void generateModel(IQuery query)
    {
	QueryParams params = generateParams();
	ModelTableQuery model = query.generateQuery(params);

	if (model != null)
	    tableData.addModel(model);
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

    private void eventRange()
    {
	ActionListener action = e -> {
	    JRadioButton button = (JRadioButton) e.getSource();
	    tfLastId.setEnabled(false);
	    tfDateStart.setEnabled(false);
	    tfDateEnd.setEnabled(false);
	    tfRequestId.setEnabled(false);
	    if (button.getText().equals("ID"))
	    {
		tfLastId.setEnabled(true);
	    }
	    if (button.getText().equals("Dates"))
	    {
		tfDateStart.setEnabled(true);
		tfDateEnd.setEnabled(true);

	    }
	    if (button.getText().equals("RequestID"))
	    {
		tfRequestId.setEnabled(true);
	    }
	};

	rbId.addActionListener(action);
	rbDates.addActionListener(action);
	rbRequestId.addActionListener(action);
    }

    private void eventWhere()
    {
	btnCustomWhere.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		CustomWhereDialog dialog = new CustomWhereDialog(columnTable, excludeCols, where);
		where = dialog.getWhere();
	    }

	});

	btnReset.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		reset();
	    }
	});
    }

    private void reset()
    {
	Date date = new Date(System.currentTimeMillis());
	dateEnd = formatter.format(date);
	date = new Date(date.getTime() - (24 * 3600000));
	dateStart = formatter.format(date);

	rbId.setSelected(true);
	tfDateStart.setEnabled(false);
	tfDateEnd.setEnabled(false);
	tfRequestId.setEnabled(false);

	tfLastId.setText("100");
	tfDateStart.setText(dateStart);
	tfDateEnd.setText(dateEnd);
	tfRequestId.setText("");

	where = "";
    }

}
