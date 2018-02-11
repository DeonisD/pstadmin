package com.lwo.pst.cards;

import java.io.Serializable;

/**
 *
 * @author drozdov_d
 */
public class BinRecord implements Serializable {

    private String column1;
    private String column2;
    private String column3;
    private String column4;

    public BinRecord() {
    }

    public BinRecord( String...objts ) {
        if ( objts.length > 0 ) column1 = objts[0];
        if ( objts.length > 1 ) column2 = objts[1];
        if ( objts.length > 2 ) column3 = objts[2];
        if ( objts.length > 3 ) column4 = objts[3];
    }
    
    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    @Override
    public String toString() {
        return "BinRecord{" + "column1=" + column1 + ", column2=" + column2 + ", column3=" + column3 + ", column4=" + column4 + '}';
    }
}
