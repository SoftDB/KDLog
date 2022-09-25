package com.softdb.kdlog.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.softdb.kdlog.action.ChangeConnect;
import com.softdb.kdlog.action.ExitApp;
import com.softdb.kdlog.action.LogAction;
import com.softdb.kdlog.action.SearchAction;
import com.softdb.kdlog.action.WinAbout;
import com.softdb.kdlog.env.EnvIcons;
import com.softdb.kdlog.env.EnvTexts;
import com.softdb.kdlog.types.Connections;

public class MenuApp extends MenuAbstract
{
    private static List<Connections> listConnections = new ArrayList<Connections>();

    private static JMenu mFile;
    private static JMenuItem mFileExit;
    private static JMenuItem mFileAbout;

    private static JMenu mLog;
    private static JMenuItem mErrorLog;
    private static JMenuItem mLogJob;
    private static JMenuItem mLogProcedureText;
    private static JMenuItem mLogOther;
    private static JMenuItem mLogEmail;
    private static JMenuItem mLogAfs;
    private static JMenuItem mLogAfh;
    private static JMenuItem mLogKdExtract;
    private static JMenuItem mLogNLMKdExtract;
    private static JMenuItem mLogKdSrcExtr;
    private static JMenuItem mLogLoadKdSrcExtr;
    private static JMenuItem mTActionLogRTTS;
    private static JMenuItem mTActionLogKDTM;
    private static JMenuItem mTActionLogKDROUTER;

    private static JMenu mSearch;
    private static JMenuItem mSearchKdConfig;
    private static JMenuItem mSearchAlOption;
    private static JMenuItem mSearchObject;
    private static JMenuItem mSearchCode;

    private static JMenu mWindow;
    private static JMenuItem mWindowCloseAll;
    private static JMenuItem mWindowCascade;

    private static JButton btnExit;
    public static JComboBox<Connections> cbConnections;

    public static void init()
    {
	create();

	initConnections();
	initMenu();
	initToolBar();

	statusMenu(false);
    }

    public static void statusMenu(Boolean val)
    {
	MenuApp.mLog.setEnabled(val);
	MenuApp.mSearch.setEnabled(val);
	MenuApp.mWindow.setEnabled(val);
    }

    private static void initMenu()
    {
	mFileExit = create("Exit", 'x', KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), new ExitApp());
	mFileAbout = create("About o...", 'A', new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent arg0)
	    {
		new WinAbout().show();
	    }
	});
	mFile = new JMenu("File");
	mFile.setMnemonic('F');
	mFile.add(mFileAbout);
	mFile.addSeparator();
	mFile.add(mFileExit);

	mErrorLog = create(EnvTexts.TITLE_ERROR_LOG, '0', KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), new LogAction(EnvTexts.TITLE_ERROR_LOG));
	mErrorLog.setToolTipText(new LoadResources().getText("error_log.txt").replace("</ver>", EnvTexts.APP_VER));
	mLogJob = create(EnvTexts.TITLE_LOG_JOB, '0', KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.ALT_MASK), new LogAction(EnvTexts.TITLE_LOG_JOB));
	mLogProcedureText = create(EnvTexts.TITLE_LOG_PROCEDURE_TEXT, 'x', KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK),
		new LogAction(EnvTexts.TITLE_LOG_PROCEDURE_TEXT));
	mLogOther = create(EnvTexts.TITLE_LOG_OTHER, '0', KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK),
		new LogAction(EnvTexts.TITLE_LOG_OTHER));
	mLogEmail = create(EnvTexts.TITLE_LOG_EMAIL, '0', new LogAction(EnvTexts.TITLE_LOG_EMAIL));
	mLogAfs = create(EnvTexts.TITLE_LOG_AFS, '0', new LogAction(EnvTexts.TITLE_LOG_AFS));

	mLogAfh = create(EnvTexts.TITLE_LOG_AFH, '0', new LogAction(EnvTexts.TITLE_LOG_AFH));

	mLogKdExtract = create(EnvTexts.TITLE_LOG_KD_EXTRACT, '0', KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK),
		new LogAction(EnvTexts.TITLE_LOG_KD_EXTRACT));
	mLogNLMKdExtract = create(EnvTexts.TITLE_LOG_KD_NLM_EXTRACT, '0', KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK),
		new LogAction(EnvTexts.TITLE_LOG_KD_NLM_EXTRACT));

	mLogKdSrcExtr = create(EnvTexts.TITLE_LOG_KD_SRC_EXTR, '0', new LogAction(EnvTexts.TITLE_LOG_KD_SRC_EXTR));
	mLogLoadKdSrcExtr = create(EnvTexts.TITLE_LOG_LOAD_KD_SRC_EXTR, '0', new LogAction(EnvTexts.TITLE_LOG_LOAD_KD_SRC_EXTR));

	mTActionLogRTTS = create(EnvTexts.TITLE_LOG_T_ACTION_RTTS, '0', new LogAction(EnvTexts.TITLE_LOG_T_ACTION_RTTS));
	mTActionLogKDTM = create(EnvTexts.TITLE_LOG_T_ACTION_KDTM, '0', new LogAction(EnvTexts.TITLE_LOG_T_ACTION_KDTM));
	mTActionLogKDROUTER = create(EnvTexts.TITLE_LOG_T_ACTION_KDROUTER, '0', new LogAction(EnvTexts.TITLE_LOG_T_ACTION_KDROUTER));

	mLog = new JMenu("Logs");
	mLog.setMnemonic('L');
	mLog.add(mErrorLog);
	mLog.addSeparator();
	mLog.add(mLogJob);
	mLog.add(mLogProcedureText);
	mLog.add(mLogOther);
	mLog.add(mLogEmail);
	mLog.add(mLogAfs);
	mLog.addSeparator();
	mLog.add(mLogAfh);
	mLog.addSeparator();
	mLog.add(mLogKdExtract);
	mLog.add(mLogNLMKdExtract);
	mLog.addSeparator();
	mLog.add(mLogKdSrcExtr);
	mLog.add(mLogLoadKdSrcExtr);
	mLog.addSeparator();
	mLog.add(mTActionLogRTTS);
	mLog.add(mTActionLogKDTM);
	mLog.add(mTActionLogKDROUTER);

	mSearchKdConfig = create(EnvTexts.TITLE_SEARCH_KD_CONFIG, '0', new SearchAction(EnvTexts.TITLE_SEARCH_KD_CONFIG));
	mSearchAlOption = create(EnvTexts.TITLE_SEARCH_AL_OPTIONS, '0', new SearchAction(EnvTexts.TITLE_SEARCH_AL_OPTIONS));
	mSearchObject = create(EnvTexts.TITLE_SEARCH_OBJECTS, '0', new SearchAction(EnvTexts.TITLE_SEARCH_OBJECTS));
	mSearchCode = create(EnvTexts.TITLE_SEARCH_CODE, '0', new SearchAction(EnvTexts.TITLE_SEARCH_CODE));

	mSearch = new JMenu("Search");
	mSearch.add(mSearchKdConfig);
	mSearch.add(mSearchAlOption);
	mSearch.addSeparator();
	mSearch.add(mSearchObject);
	mSearch.add(mSearchCode);

	mWindowCloseAll = create("Close all", 'C', new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent arg0)
	    {
		for (int i = 0; i < countFrames; i++)
		{
		    try
		    {
			JInternalFrame frame = (JInternalFrame) htFrames.get(i);
			frame.setClosed(true);
			htFrames.remove(frame);
		    } catch (PropertyVetoException e)
		    {
			e.printStackTrace();
		    }
		}

		mWindowCloseAll.setEnabled(false);
		mWindowCascade.setEnabled(false);
		countFrames = 0;
	    }
	});

	mWindowCascade = create("Cascade", 'a', new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
		int x = 10;
		int y = 10;

		for (int i = 0; i < countFrames; i++)
		{
		    JInternalFrame frame = (JInternalFrame) htFrames.get(i);
		    frame.setLocation(x, y);
		    frame.setBounds(x, y, Config.getWidthFrame(), Config.getHeightFrame());
		    x = x + 25;
		    y = y + 25;
		}
	    }

	});

	mWindow = new JMenu("Window");
	mWindow.setMnemonic('W');
	mWindow.add(mWindowCloseAll);
	mWindow.add(mWindowCascade);

	menuBar.add(mFile);
	menuBar.add(mLog);
	menuBar.add(mSearch);
	menuBar.add(mWindow);
    }

    private static void initToolBar()
    {
	DefaultComboBoxModel<Connections> modelConnections = new DefaultComboBoxModel<Connections>(new Vector<Connections>(listConnections));
	cbConnections = new JComboBox<Connections>(modelConnections);
	cbConnections.setSelectedIndex(-1);
	cbConnections.addActionListener(new ChangeConnect());

	btnExit = new JButton(new LoadResources().getIcon(EnvIcons.EXIT_APP));
	btnExit.setToolTipText("Exit");
	btnExit.addActionListener(new ExitApp());
	toolBar.add(btnExit);
	toolBar.addSeparator();
	toolBar.add(new JLabel("Connections"));
	toolBar.addSeparator();
	toolBar.add(cbConnections);
	toolBar.addSeparator();
    }

    private static void initConnections()
    {
	try
	{
	    File fXmlFile = new File("connections.xml");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc;

	    doc = dBuilder.parse(fXmlFile);
	    doc.getDocumentElement().normalize();

	    NodeList list = (NodeList) doc.getElementsByTagName("connect");
	    for (int x = 0; x < list.getLength(); x++)
	    {
		Connections connections = new Connections();
		Element element = (Element) list.item(x);
		connections.setName(element.getAttribute("name"));
		connections.setHost(element.getAttribute("host"));
		connections.setPort(element.getAttribute("port"));
		connections.setSid(element.getAttribute("sid"));
		connections.setUser(element.getAttribute("user"));
		connections.setPass(element.getAttribute("pass"));
		listConnections.add(connections);
	    }

	} catch (Exception e)
	{
	    System.out.println(e.getMessage());
	}
    }

}
