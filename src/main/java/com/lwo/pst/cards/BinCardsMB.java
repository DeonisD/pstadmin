package com.lwo.pst.cards;

import com.lwo.pst.util.Utils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author drozdov_d
 */
@Named
@RequestScoped
public class BinCardsMB implements Serializable {
    
    @Inject CardsService service;
    
    private boolean exist = false;
    private Date lastModified;
    private List<BinRecord> records = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        
        File cards = new File( FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + Utils.getProperty( "admin.cardPath", "data/cards.csv" ) );
        if ( cards.exists() ){
            this.exist          = true;
            this.lastModified   = new Date( cards.lastModified() );
            this.records        = service.getListBin();
        }
    }

    
    
    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public List<BinRecord> getRecords() {
        return records;
    }

    public void setRecords(List<BinRecord> records) {
        this.records = records;
    }
    
    
    
}
