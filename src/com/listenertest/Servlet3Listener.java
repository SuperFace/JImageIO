package com.listenertest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Servlet3Listener
 *
 */
@WebListener
public class Servlet3Listener implements ServletContextListener{
	
    public Servlet3Listener() {
        // TODO Auto-generated constructor stub
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("开启servlet3监听");
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
}
