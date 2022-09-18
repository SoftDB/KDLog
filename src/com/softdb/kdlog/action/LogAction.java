package com.softdb.kdlog.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.softdb.kdlog.app.App;
import com.softdb.kdlog.env.Env;
import com.softdb.kdlog.env.EnvTexts;
import com.softdb.kdlog.frame.LogFrame;
import com.softdb.kdlog.types.FrameParams;

public class LogAction implements ActionListener
{
    FrameParams frameParams;

    public LogAction(String title)
    {
	frameParams = new FrameParams();
	frameParams.setTitle(title);

	if (title.equals(EnvTexts.TITLE_ERROR_LOG))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("DR");
	    frameParams.setTableName("ERROR_LOG");
	    frameParams.setColId("ELO_ID");
	    frameParams.setColDate("ELO_TIME");
	    frameParams.setExcludeCols(new String[] { "ELO_TIME", "ELO_ID" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_JOB))
	{
	    frameParams.setReq(Env.YES_REQUEST_ID);
	    frameParams.setOwnerTable("DR");
	    frameParams.setTableName("LOG_JOB");
	    frameParams.setColId("PROCESS_ID");
	    frameParams.setColDate("START_DATE");
	    frameParams.setExcludeCols(new String[] { "PROCESS_ID", "START_DATE", "FINISH_DATE", "REQUEST_ID" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_PROCEDURE_TEXT))
	{
	    frameParams.setReq(Env.YES_REQUEST_ID);
	    frameParams.setOwnerTable("DR");
	    frameParams.setTableName("LOG_PROCEDURE_TEXT");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("LOG_DATE");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "LOG_DATE" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_OTHER))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("DR");
	    frameParams.setTableName("LOG_OTHER");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("LOG_DATE");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "LOG_DATE" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_EMAIL))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("DR");
	    frameParams.setTableName("LOG_EMAIL");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("OPERATION_TIME");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "OPERATION_TIME" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_AFS))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("DR");
	    frameParams.setTableName("LOG_AFS");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("LOG_DATE");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "LOG_DATE" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_AFH))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("AL");
	    frameParams.setTableName("LOG_AFH");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("LOG_DATE");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "LOG_DATE" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_KD_EXTRACT))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("KD_EXTRACT");
	    frameParams.setTableName("LOG");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("LOG_DATE");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "LOG_DATE" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_KD_NLM_EXTRACT))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("KD_EXTRACT");
	    frameParams.setTableName("LOG_KD_EXTRACT");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("LOG_DATE");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "LOG_DATE" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_KD_SRC_EXTR))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("KD_SRC_EXTR");
	    frameParams.setTableName("LOG");
	    frameParams.setColId("LOG_ID");
	    frameParams.setColDate("LOG_DATE");
	    frameParams.setExcludeCols(new String[] { "LOG_ID", "LOG_DATE" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_LOAD_KD_SRC_EXTR))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);
	    frameParams.setOwnerTable("KD_SRC_EXTR");
	    frameParams.setTableName("LOG_LOAD");
	    frameParams.setColId("ID");
	    frameParams.setColDate("D_START");
	    frameParams.setExcludeCols(new String[] { "ID", "D_START", "D_END", "D_EXPORT", "D_IMPORT", "D_DATA_DANYCH" });
	}

	if (title.equals(EnvTexts.TITLE_LOG_T_ACTION_RTTS) || title.equals(EnvTexts.TITLE_LOG_T_ACTION_KDTM)
		|| title.equals(EnvTexts.TITLE_LOG_T_ACTION_KDROUTER))
	{
	    frameParams.setReq(Env.NO_REQUEST_ID);

	    if (title.equals(EnvTexts.TITLE_LOG_T_ACTION_RTTS))
		frameParams.setOwnerTable("KDPREVENT_RTTS_ADMIN");
	    if (title.equals(EnvTexts.TITLE_LOG_T_ACTION_KDTM))
		frameParams.setOwnerTable("KDTM");
	    if (title.equals(EnvTexts.TITLE_LOG_T_ACTION_KDROUTER))
		frameParams.setOwnerTable("KDROUTER");

	    frameParams.setTableName("T_ACTION_LOG");
	    frameParams.setColId("ID");
	    frameParams.setColDate("ACTION_TIME");
	    frameParams.setExcludeCols(new String[] { "ID", "ACTION_TIME" });
	}

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
	LogFrame frame = new LogFrame(frameParams);
	App.getDesktop().add(frame);
	frame.setVisible(true);
    }

}
