package com.softdb.kdlog.action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.softdb.kdlog.app.App;
import com.softdb.kdlog.app.LoadResources;
import com.softdb.kdlog.env.EnvTexts;

public class WinAbout
{
    public void show()
    {
	JEditorPane txtLicense = new JEditorPane();
	txtLicense.setPreferredSize(new Dimension(300, 200));
	txtLicense.setContentType("text/html");
	txtLicense.setEditable(false);

	txtLicense.setText(new LoadResources().getText("about.txt").replace("</ver>", EnvTexts.APP_VER));

	JPanel panelA = new JPanel();
	panelA.setLayout(new BorderLayout());
	panelA.add(new JScrollPane(txtLicense), BorderLayout.CENTER);

	JOptionPane.showConfirmDialog(App.getMainFrame(), panelA, "About o KDLog...", JOptionPane.CLOSED_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
