package com.softdb.kdlog.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.softdb.kdlog.app.App;
import com.softdb.kdlog.env.EnvTexts;
import com.softdb.kdlog.frame.SearchFrame;
import com.softdb.kdlog.types.FrameParams;

public class SearchAction implements ActionListener
{

    FrameParams frameParams;

    public SearchAction(String title)
    {
	frameParams = new FrameParams();
	frameParams.setTitle(title);

	if (title.equals(EnvTexts.TITLE_SEARCH_KD_CONFIG))
	{
	    frameParams.setOwnerTable("AL");
	    frameParams.setTableName("KD_CONFIG");
	    frameParams.setColSearch("par_name");
	}

	if (title.equals(EnvTexts.TITLE_SEARCH_AL_OPTIONS))
	{
	    frameParams.setOwnerTable("AL");
	    frameParams.setTableName("AL_OPTIONS");
	    frameParams.setColSearch("option_name");
	}

	if (title.equals(EnvTexts.TITLE_SEARCH_OBJECTS))
	{
	    frameParams.setOwnerTable("SYS");
	    frameParams.setTableName("ALL_OBJECTS");
	    frameParams.setColSearch("object_name");
	}
	if (title.equals(EnvTexts.TITLE_SEARCH_CODE))
	{
	    frameParams.setOwnerTable("SYS");
	    frameParams.setTableName("USER_SOURCE");
	    frameParams.setColSearch("text");
	}

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	SearchFrame frame = new SearchFrame(frameParams);
	App.getDesktop().add(frame);
	frame.setVisible(true);
    }

}
