package com.lwo.entity;

import java.util.List;

/**
 *
 * @author drozdov_d
 */
public class UpdateStatus extends Response {
    
    private FileStatus directory;

    public UpdateStatus() {
    }

    public UpdateStatus( List<RemoteFile> files ) {
        this.directory = new FileStatus(files);
    }
    
    public FileStatus getDirectory() {
        return directory;
    }

    public void setDirectory(FileStatus directory) {
        this.directory = directory;
    }
    
    public void setDirectory( List<RemoteFile> files ) {
        this.directory = new FileStatus(files);
    }
}
