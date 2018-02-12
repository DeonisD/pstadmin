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
    
    @PostConstruct
    public void init() {
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