package com.softdb.kdlog.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JRadioButton;

public abstract class AbstractLogFrameEvent extends AbstractLogFramePanel
{
    private static final long serialVersionUID = -1864501367792232247L;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private String dateStart;
    private String dateEnd;

    protected String[] excludeCols;
    protected String where = "";

    public AbstractLogFrameEvent(String title, Boolean req)
    {
	super(title, req);
	
	eventRange();
	eventWhere();
	reset();
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
