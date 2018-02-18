package com.lwo.pst.city;

import com.lwo.dbf.core.DbfMetadata;
import com.lwo.dbf.core.DbfRecord;
import com.lwo.dbf.reader.DbfReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
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
public class CityMB implements Serializable {
    
    private boolean exist = false;
    private Date lastModified;
    private final List<City> cities         = new ArrayList<>();
    private List<City> filteredCities       = new ArrayList<>();
    
    @PostConstruct
    public void onInit() {
        readDBF();
    }
    
    public void readDBF(){
        
        Charset stringCharset = Charset.forName("Cp866");
        File dbf              = new File( System.getProperty( "catalina.base", "" ) + "/pstdata/dbf/city.dbf" );
        
        if ( dbf.exists() ){
            cities.clear();
            exist             = true;
            this.lastModified   = new Date( dbf.lastModified() );
            DbfRecord rec;
            try {
                try (InputStream is = new FileInputStream(dbf); DbfReader reader = new DbfReader( is )) {
                    DbfMetadata meta = reader.getMetadata();
                    
                    System.out.println("Read DBF Metadata: " + meta);
                    while ((rec = reader.read()) != null) {
                        rec.setStringCharset(stringCharset);
                        City    city = new City();
                                city.setSOATO( rec.getString("SOATO"));
                                city.setTIP( rec.getString("TIP") );
                                city.setNAME(rec.getString("NAME") );
                                city.setOBL(rec.getString("OBL") );
                                city.setRAION(rec.getString("RAION") );
                                city.setSOVET(rec.getString("SOVET") );
                                city.setGNI(rec.getString("GNI") );
                                city.setDATEL(rec.getString("DATEL") );
                                city.setSOATON(rec.getString("SOATON") );
                                city.setDATAV(rec.getString("DATAV") );
                        this.cities.add(city);
                    }
                }
                    
                
            } catch (Exception e ){
                e.printStackTrace( System.err );
            }
        } else {
            exist = false;
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

    public List<City> getCities() {
        return cities;
    }

    public void setFilteredCities( List<City> filteredCities ) {
        System.out.println("--> " + filteredCities );
        this.filteredCities = filteredCities;
    }

    public List<City> getFilteredCities() {
        return filteredCities;
    }
}
