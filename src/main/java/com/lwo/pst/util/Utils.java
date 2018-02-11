package com.lwo.pst.util;

import java.io.InputStream;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.Properties;

/**
 *
 * @author drozdov_d
 */
@ApplicationScoped
public class Utils implements Serializable {

    @PostConstruct
    public void init() {
       
    }

    public static void addDetailMessage(String message) {
        addDetailMessage(message, null);
    }

    public static void addDetailMessage(String message, FacesMessage.Severity severity) {
        FacesMessage facesMessage = Messages.create("").detail(message).get();
        if (severity != null && severity != FacesMessage.SEVERITY_INFO) {
            facesMessage.setSeverity(severity);
        }
        Messages.add(null, facesMessage);
    }
    
    public static String getProperty( String propertyName, String defaultValue ){
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Properties adminConfigFile = new Properties();
        
        try (InputStream is = cl.getResourceAsStream(("admin-config.properties"))) {
            adminConfigFile.load(is);
        } catch (Exception ex) {
            ex.printStackTrace( System.err );
        }
        
            return adminConfigFile.getProperty( propertyName, defaultValue);
    }
    
}