package com.lwo.entity;

/**
 *
 * @author drozdov_d
 */
public class RemoteFile {
    private String path;
    private String fileName;
    private Long   lastModified;

    public RemoteFile() {
    
    }

    public RemoteFile(String path, String fileName, Long lastModified ) {
        this.path           = path;
        this.fileName       = fileName;
        this.lastModified   = lastModified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String downloadURL) {
        this.path = downloadURL;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }
}
