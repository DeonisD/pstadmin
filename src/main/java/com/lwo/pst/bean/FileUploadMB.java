package com.lwo.pst.bean;

import com.lwo.pst.util.Utils;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
    
    private UploadedFile file;
    //private final String CARDS_FILENAME = "data/cards.csv";
    private String fileName;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void upload() {
        System.out.println("--> file == null " + ( file == null ) );
        if( file != null ) {

            File targetFile = new File( fileName );
                 targetFile.mkdirs();
            try {
                Files.copy( file.getInputstream(),  targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch ( Exception e ){
                e.printStackTrace( System.err );
            }
        }
    }
    
    public void handleUpload(FileUploadEvent event) {
        this.file       = event.getFile();
        this.fileName   = System.getProperty( "catalina.base", "" ) + "/pstdata/cards.csv";
        upload();
    }  
    
    public void handleDBFUpload(FileUploadEvent event) {
        this.file       = event.getFile();
        this.fileName   = System.getProperty( "catalina.base", "" ) + "/pstdata/dbf/city.dbf";
        upload();
    }  
    
}
