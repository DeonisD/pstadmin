package com.lwo.pst.city;

/**
 *
 * @author drozdov_d
 */
public class City {
    private int Id;
    private String SOATO;
    private String TIP;
    private String NAME;
    private String OBL;
    private String RAION;
    private String SOVET;
    private String GNI;
    private String DATEL;
    private String SOATON;
    private String DATAV;

    public City() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getSOATO() {
        return SOATO;
    }

    public void setSOATO(String SOATO) {
        this.SOATO = SOATO;
    }

    public String getTIP() {
        return TIP;
    }

    public void setTIP(String TIP) {
        this.TIP = TIP;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getOBL() {
        return OBL;
    }

    public void setOBL(String OBL) {
        this.OBL = OBL;
    }

    public String getRAION() {
        return RAION;
    }

    public void setRAION(String RAION) {
        this.RAION = RAION;
    }

    public String getSOVET() {
        return SOVET;
    }

    public void setSOVET(String SOVET) {
        this.SOVET = SOVET;
    }

    public String getGNI() {
        return GNI;
    }

    public void setGNI(String GNI) {
        this.GNI = GNI;
    }

    public String getDATEL() {
        return DATEL;
    }

    public void setDATEL(String DATEL) {
        this.DATEL = DATEL;
    }

    public String getSOATON() {
        return SOATON;
    }

    public void setSOATON(String SOATON) {
        this.SOATON = SOATON;
    }

    public String getDATAV() {
        return DATAV;
    }

    public void setDATAV(String DATAV) {
        this.DATAV = DATAV;
    }

    @Override
    public String toString() {
        return "City{" + "SOATO=" + SOATO + ", TIP=" + TIP + ", NAME=" + NAME + ", OBL=" + OBL + ", RAION=" + RAION + ", SOVET=" + SOVET + ", GNI=" + GNI + ", DATEL=" + DATEL + ", SOATON=" + SOATON + ", DATAV=" + DATAV + '}';
    }
}
