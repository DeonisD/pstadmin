package com.lwo.pst.cards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author drozdov_d
 */
@Named
@RequestScoped
public class BinCardsMB implements Serializable {
    
    private boolean exist = false;
    private Date lastModified;
    private List<BinRecord> records = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        
        File cards = new File( System.getProperty( "catalina.base", "" ) + "/pstdata/cards.csv" );
        if ( cards.exists() ){
            this.exist          = true;
            this.lastModified   = new Date( cards.lastModified() );
            setRecords( cards );
        }
    }

    private void setRecords( File file ){
        
        this.records.clear();
        
        try (BufferedReader br = new BufferedReader(new FileReader( file ))) {
            String line, cvsSplitBy = ";";
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                BinRecord record = new BinRecord( country );
                        this.records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace( System.err );
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
