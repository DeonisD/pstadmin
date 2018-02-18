package com.lwo.entity.terminal;

import com.lwo.entity.BankNote;
import com.lwo.entity.TerminalInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;


/**
 *
 * @author drozdov_d
 */
@Stateless
public class TerminalService {
    
    @Resource(name = "h2") private DataSource datasource; 
    
    public void updateTerminalInfo( TerminalInfo info ){
        
        String sql   = "merge into terminal( uuid, title, workPlaceId, moduleId, mode, owner, address, soato, zip, t_id, ip, mac, lu_date) key( uuid ) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn         = null;
        PreparedStatement ps    = null;
        
        try {
            conn = datasource.getConnection();
            ps   = conn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1  , info.getUUID() );
            ps.setString( 2  , info.getTitle());
            ps.setString( 3  , info.getWorkPlaceId());
            ps.setString( 4  , info.getModuleId());
            ps.setString( 5  , info.getMode());
            ps.setString( 6  , info.getOwner());
            ps.setString( 7  , info.getAddress());
            ps.setString( 8  , info.getSoato());
            ps.setString( 9  , info.getZip());
            ps.setString( 10 , info.getId());
            ps.setString( 11 , info.getIp());
            ps.setString( 12 , info.getMac());
            ps.setTimestamp(13, new java.sql.Timestamp( System.currentTimeMillis() ));
            ps.execute();
            conn.commit();
            
            ps  = conn.prepareStatement( "delete from cashin where terminal_id = (select id from terminal where uuid=?)" );
            ps.setString(1,  info.getUUID()  );
            ps.execute();
            conn.commit();
            
            if ( null != info.getBanknotes() || !info.getBanknotes().isEmpty() ){
                conn.setAutoCommit(false);
                ps  = conn.prepareStatement( "insert into cashin( terminal_id, value, currency, amount, count ) VALUES ( (select id from terminal where uuid=?),?,?,?,?)" );
                for ( BankNote b : info.getBanknotes() ){
                    ps.setString( 1, info.getUUID() );
                    ps.setDouble( 2, b.getValue() );
                    ps.setString( 3, b.getCurrency() );
                    ps.setDouble( 4, b.getAmount());
                    ps.setInt(    5, b.getCount());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
            }
                    
        } catch ( Exception e ){
            e.printStackTrace( System.err );
        } finally {
            if ( null != ps )   try { ps.close(); }   catch(Exception ignore){}
            if ( null != conn ) try { conn.close(); } catch(Exception ignore){}
        }
        
    }
    
    public List<TerminalInfo> getListTerminals(){
        List<TerminalInfo> terminals    = new ArrayList<>();
        Connection conn                 = null;
        Statement          stat         = null;
        try {
            conn = datasource.getConnection();
            stat = conn.createStatement();
            try (ResultSet rs = stat.executeQuery("select * from terminal")) {
                while (rs.next()) {
                    TerminalInfo    info = new TerminalInfo();
                                    info.setUUID(  rs.getString("UUID"));
                                    info.setTitle( rs.getString("TITLE"));
                                    info.setWorkPlaceId( rs.getString( "WORKPLACEID" ));
                                    info.setModuleId(  rs.getString("MODULEID"));
                                    info.setMode( rs.getString("MODE"));
                                    info.setOwner( rs.getString("OWNER"));
                                    info.setAddress( rs.getString("ADDRESS") );
                                    info.setSoato( rs.getString("SOATO"));
                                    info.setZip( rs.getString("ZIP"));
                                    info.setId( rs.getString("T_ID"));
                                    info.setIp( rs.getString("IP"));
                                    info.setMac( rs.getString("MAC"));
                                    info.setLu_date(  new Date(rs.getTimestamp("LU_DATE").getTime())  ); //rs.getDate("LU_DATE")
                        terminals.add(info);
                }
            }
            
        } catch ( Exception e ){
            e.printStackTrace( System.err );
        } finally {
            if ( null != stat ) try { stat.close(); } catch(Exception ignore){}
            if ( null != conn ) try { conn.close(); } catch(Exception ignore){}
        }   
            return terminals;
    }
    
    
}
