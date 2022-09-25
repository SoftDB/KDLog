package com.softdb.kdlog.types;

public class Connections 
{
    private String name;
    private String host;
    private String port;
    private String sid;
    private String user;
    private String pass;

    public Connections(String name, String host, String port, String sid, String user, String pass) 
    {
	super();
	
	this.name = name;
	this.host = host;
	this.port = port;
	this.sid = sid;
	this.user = user;
	this.pass = pass;
    }

    public Connections()
    {

    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getHost()
    {
	return host;
    }

    public void setHost(String host)
    {
	this.host = host;
    }

    public String getPort()
    {
	return port;
    }

    public void setPort(String port)
    {
	this.port = port;
    }

    public String getSid()
    {
	return sid;
    }

    public void setSid(String sid)
    {
	this.sid = sid;
    }

    public String getUser()
    {
	return user;
    }

    public void setUser(String user)
    {
	this.user = user;
    }

    public String getPass()
    {
	return pass;
    }

    public void setPass(String pass)
    {
	this.pass = pass;
    }
    
    @Override
    public String toString()
    {
	return name;	
    }
}
