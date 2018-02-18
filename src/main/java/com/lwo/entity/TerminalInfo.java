package com.lwo.entity;

import java.security.MessageDigest;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author drozdov_d
 */
public class TerminalInfo {
    private String title;
    private String workPlaceId;
    private String moduleId;
    private String mode;
    private String owner;
    private String address;
    private String soato;
    private String zip;
    private String id;
    private String ip;
    private String mac;
    private Date lu_date;
    private List<BankNote> banknotes;
    
    private String uuid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkPlaceId() {
        return workPlaceId;
    }

    public void setWorkPlaceId(String workPlaceId) {
        this.workPlaceId = workPlaceId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSoato() {
        return soato;
    }

    public void setSoato(String soato) {
        this.soato = soato;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    
    public String getUUID(){
        if ( null == this.uuid ){
            this.uuid = this.workPlaceId + ":" + this.id + ":" + this.mac + ":" + this.moduleId;
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                              md.update( uuid.getBytes() );
                    byte byteData[] = md.digest();
                    StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < byteData.length; i++) sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                this.uuid = sb.toString();
            } catch ( Exception ignore ){}
        }
            return uuid;
    }
    
    public void setUUID( String uuid ){
        this.uuid = uuid;
    }
    
    public List<BankNote> getBanknotes() {
        return banknotes;
    }

    public void setBanknotes(List<BankNote> banknotes) {
        this.banknotes = banknotes;
    }

    public Date getLu_date() {
        return lu_date;
    }

    public void setLu_date(Date lu_date) {
        this.lu_date = lu_date;
    }
    @Override
    public String toString() {
        return "TerminalInfo{" + "title=" + title + ", workPlaceId=" + workPlaceId + ", moduleId=" + moduleId + ", mode=" + mode + ", owner=" + owner + ", address=" + address + ", soato=" + soato + ", zip=" + zip + ", id=" + id + ", ip=" + ip + ", mac=" + mac + ", banknotes=" + banknotes + '}';
    }
}
