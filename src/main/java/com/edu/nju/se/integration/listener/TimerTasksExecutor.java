package com.edu.nju.se.integration.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by darxan on 2017/3/14.
 */
@WebListener
public class TimerTasksExecutor implements ServletContextListener{

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor ;

    private int POOL_SIZE = 2;

    public void contextInitialized(ServletContextEvent sce) {

        scheduledThreadPoolExecutor =
                new ScheduledThreadPoolExecutor(POOL_SIZE);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
