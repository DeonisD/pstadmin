package com.lwo.pst.bean;

import com.lwo.dbf.core.DbfMetadata;
import com.lwo.dbf.core.DbfRecord;
import com.lwo.dbf.reader.DbfReader;
import com.lwo.pst.cards.BinRecord;
import com.lwo.pst.cards.CardsService;
import com.lwo.pst.city.City;
import com.lwo.pst.city.CityService;
import com.lwo.pst.util.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author drozdov_d
 */
@Named( value = "fileUpload")
@RequestScoped
public class FileUploadMB {
    
    @Inject CardsService service;
    @Inject CityService  city_service;
    
    private UploadedFile file;
    private String fileName;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void upload() {
        if( file != null ) {
            File targetFile = new File( FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + fileName );
                 targetFile.mkdirs();
            try {
                Files.copy( file.getInputstream(),  targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                if      ( file.getFileName().equalsIgnoreCase("cards.csv") )    setRecords(targetFile);
                else if ( file.getFileName().equalsIgnoreCase("city.dbf") )     readDBF( targetFile );
                
            } catch ( IOException e ){
                e.printStackTrace( System.err );
            }
        }
    }
    
    public void handleUpload(FileUploadEvent event) {
        this.file       = event.getFile();
        this.fileName   = Utils.getProperty( "admin.cardPath", "data/cards.csv" );
        upload();
    }  
    
    public void handleDBFUpload(FileUploadEvent event) {
        this.file       = event.getFile();
        this.fileName   = Utils.getProperty( "admin.dbfPath", "data/city.dbf" );
        upload();
    }  
    
    private void setRecords( File file ){
        List<BinRecord> records = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader( file ))) {
            String line, cvsSplitBy = ";";
            int i = 1;
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                BinRecord record = new BinRecord( country );
                          record.setId(i);
                          records.add(record);
                    i++;      
            }
            
                service.saveListBin( records );
            
        } catch (IOException e) {
            e.printStackTrace( System.err );
        }
    }
    
    public void readDBF( File dbf ){
        List<City> cities = new ArrayList<>();
        Charset stringCharset = Charset.forName("Cp866");
        if ( dbf.exists() ){
            DbfRecord rec;
            try {
                try (InputStream is = new FileInputStream(dbf); DbfReader reader = new DbfReader( is )) {
                    DbfMetadata meta    = reader.getMetadata();
                    
                    System.out.println("Read DBF Metadata: " + meta);
                    while ((rec = reader.read()) != null) {
                        rec.setStringCharset(stringCharset);
                        City city = new City();
                        city.setId( rec.getRecordNumber() );
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
                            cities.add(city);
                    }
                }
                    city_service.saveListCity(cities);
                
            } catch (IOException e ){
                e.printStackTrace( System.err );
            }
        } 
    }
    
    
}
