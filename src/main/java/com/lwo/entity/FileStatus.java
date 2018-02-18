package com.lwo.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drozdov_d
 */
public class FileStatus {
    
    
    private List<RemoteFile> files = new ArrayList<>(); 

    public FileStatus() {
    }
    
    public FileStatus(List<RemoteFile> files) {
        this.files = files;
    }

    public List<RemoteFile> getFiles() {
        return files;
    }

    public void setFiles(List<RemoteFile> files) {
        this.files = files;
    }
}
