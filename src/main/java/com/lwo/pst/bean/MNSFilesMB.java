package com.lwo.pst.bean;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author drozdov_d
 */
@Named
@RequestScoped
public class MNSFilesMB implements Serializable {
 
    private final String root_folder = System.getProperty( "catalina.base", "" ) + "/pstdata/";
    private List<File> filesInFolder;
    
    @PostConstruct
    public void onInit() {
        try {
            this.filesInFolder = Files.walk(Paths.get( root_folder + "mnsInfo" )).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
        } catch ( Exception e ){
            e.printStackTrace( System.err );
        }    
    }

    public List<File> getFilesInFolder() {
        return filesInFolder;
    }

    public void setFilesInFolder(List<File> filesInFolder) {
        this.filesInFolder = filesInFolder;
    }
}
