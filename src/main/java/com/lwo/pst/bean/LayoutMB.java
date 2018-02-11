package com.lwo.pst.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author drozdov_d
 */
@Named
@SessionScoped
public class LayoutMB implements Serializable {

    private String layout;
    
    @Resource(name = "h2")    private DataSource datasource;

    @PostConstruct
    public void init() {
        System.out.println("--> " + (datasource == null) );
        try {
            System.out.println("--> " + (datasource.getConnection().nativeSQL("select * from test")) );
        } catch ( Exception e ){
            e.printStackTrace( System.err );
        }    
        setDefaultLayout();
    }

    public String getLayout() {
        return layout;
    }

    public void setHorizontalLayout() {
        layout = "/WEB-INF/templates/template-horizontal.xhtml";
    }

    public void setDefaultLayout() {
        layout = "/WEB-INF/templates/template.xhtml";
    }
}