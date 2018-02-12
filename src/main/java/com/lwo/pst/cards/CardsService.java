package com.lwo.pst.cards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;


/**
 *
 * @author Deonis
 */
@Stateless
public class CardsService {
    
    @Resource(name = "h2")    private DataSource datasource;
    
    public List<BinRecord> getListBin(){
        List<BinRecord> bins    = new ArrayList<>();
        Connection conn         = null ;
        Statement stat          = null;
        
        try {
            conn = datasource.getConnection();
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from cardbin");
            
            while (rs.next()) {
                BinRecord bin = new BinRecord();
                          bin.setId(      rs.getInt("id") );
                          bin.setColumn1( rs.getString("column1") );
                          bin.setColumn2( rs.getString("column2") );
                          bin.setColumn3( rs.getString("column3") );
                          bin.setColumn4( rs.getString("column4") );
                    bins.add(bin);
            }
            
        } catch ( SQLException e ){
            e.printStackTrace( System.err );
        } finally {
            if ( null != stat ) try{ stat.close(); } catch ( SQLException ignore ){}
            if ( null != conn ) try{ conn.close(); } catch ( SQLException ignore ){}
        }
        
            return bins;
    }
    
    
    public void saveListBin( List<BinRecord> records ){
        String query = "insert into cardbin (id, column1, column2, column3, column4) VALUES (?,?,?,?,?)";
        Connection conn         = null ;
        PreparedStatement ps;
        
        try {
            conn = datasource.getConnection();
            conn.setAutoCommit(false);
            ps   = conn.prepareStatement("delete from cardbin");
            ps.execute();
            ps   = conn.prepareStatement(query);
            for ( BinRecord record : records  ){
                ps.setInt(1, record.getId() );
                ps.setString( 2 , record.getColumn1() );
                ps.setString( 3 , record.getColumn2() );
                ps.setString( 4 , record.getColumn3() );
                ps.setString( 5 , record.getColumn4() );
                ps.addBatch( );
            }
            ps.executeBatch();
            
            conn.commit();
            
        } catch ( SQLException se ){
            se.printStackTrace( System.err );
            try { conn.rollback(); } catch ( SQLException ignore){}
        } finally {
            if ( null != conn ) try{ conn.close(); } catch ( SQLException ignore ){}
        }
        
        
    }
    
    
}
