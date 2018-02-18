package com.lwo.servlets.listener;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 *
 * @author drozdov_d
 */
@WebListener
public class DbStarter implements ServletContextListener {

    @Resource(name = "h2") private DataSource datasource;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("DbStarter start initialize");
        ClassLoader loader  = Thread.currentThread().getContextClassLoader();
        Connection conn     = null;
        try {
            conn = datasource.getConnection();
            try (Statement stat = conn.createStatement()) {
                URL url = loader.getResource("/scripts/dictionary/");
                String path = url.getPath();
                for (File file : new File(path).listFiles()) {
                    if (file.getName().toLowerCase().endsWith(".sql")) {
                        System.out.println("load " + file.getName());
                        stat.execute("runscript from 'classpath:/scripts/dictionary/" + file.getName() + "'");
                    }
                }
                    
                /*
                if ( 0 == shemas.getOrDefault("PST", 0)){
                        System.out.println("create tables for pst");
                        URL url         = loader.getResource("/scripts/dictionary/");
                        String path     = url.getPath();
                        File file       = new File(path);
                            stat.execute( "runscript from 'classpath:/scripts/create_pst.sql'" );
                    }
                */    
            }
            
                System.out.println( "DbStarter end initialize" );
        } catch (Exception e) {
            e.printStackTrace( System.err );
        } finally {
            try { if (null != conn ) conn.close(); } catch (Exception e) {}
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection conn = null;
        try {
            conn = datasource.getConnection();
            try (Statement stat = conn.createStatement()) {
                stat.execute("SHUTDOWN");
            }
        } catch (Exception e) {
            e.printStackTrace( System.err );
        } finally {
            try { if (null != conn ) conn.close(); } catch (Exception e) {}
        }    
    }
    
}
