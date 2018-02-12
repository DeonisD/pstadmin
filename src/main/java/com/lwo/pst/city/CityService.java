package com.lwo.pst.city;

import java.io.Serializable;
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
public class CityService implements Serializable {
 
    @Resource(name = "h2")    private DataSource datasource;
    
    public List<City> getListCity(){
        List<City> cities    = new ArrayList<>();
        Connection conn      = null ;
        Statement stat       = null;
        
        try {
            conn = datasource.getConnection();
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from city");
            
            while (rs.next()) {
                City city = new City();
                     city.setId( rs.getInt("id") );
                     city.setSOATO( rs.getString("soato") );
                     city.setTIP( rs.getString("tip") );
                     city.setNAME( rs.getString( "name" ) );
                     city.setOBL( rs.getString("obl") );
                     city.setRAION( rs.getString("raion") );
                     city.setSOVET( rs.getString("sovet") );
                     city.setGNI( rs.getString("gni") );
                     city.setDATEL( rs.getString("datel") );   
                     city.setSOATON( rs.getString("soaton") );   
                     city.setDATAV( rs.getString("datav") );   

                    cities.add( city );
            }
            
        } catch ( SQLException e ){
            e.printStackTrace( System.err );
        } finally {
            if ( null != stat ) try{ stat.close(); } catch ( SQLException ignore ){}
            if ( null != conn ) try{ conn.close(); } catch ( SQLException ignore ){}
        }
        
            return cities;
    }
    
    public void saveListCity( List<City> records ){
        String query = "insert into city (id,soato,tip,name,obl,raion,sovet,gni,datel,soaton,datav) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn         = null ;
        PreparedStatement ps    = null;
        
        try {
            conn = datasource.getConnection();
            conn.setAutoCommit(false);
            ps   = conn.prepareStatement("delete from city");
            ps.execute();
            ps   = conn.prepareStatement(query);
            for ( City record : records  ){
                ps.setInt(1,         record.getId() );
                ps.setString( 2  ,   record.getSOATO() );
                ps.setString( 3  ,   record.getTIP() );
                ps.setString( 4  ,   record.getNAME() );
                ps.setString( 5  ,   record.getOBL() );
                ps.setString( 6  ,   record.getRAION() );
                ps.setString( 7  ,   record.getSOVET() );
                ps.setString( 8  ,   record.getGNI() ); 
                ps.setString( 9  ,   record.getDATEL() ); 
                ps.setString( 10 ,   record.getSOATON() );
                ps.setString( 11 ,   record.getDATAV() );
                ps.addBatch();
            }
            ps.executeBatch();
            
            conn.commit();
            
        } catch ( SQLException se ){
            se.printStackTrace( System.err );
            try { conn.rollback(); } catch ( SQLException ignore){}
        } finally {
             if ( null != ps )  try{ ps.close(); } catch ( SQLException ignore ){}
            if ( null != conn ) try{ conn.close(); } catch ( SQLException ignore ){}
        }
    }
    
    public List<City> getListCity( String searchValue ){
        List<City> cities       = new ArrayList<>();
        Connection conn         = null;
        PreparedStatement stat  = null;
        
        try {
            conn = datasource.getConnection();
            stat = conn.prepareStatement("SELECT * FROM CITY where soato like ? or tip like ? or name like ? or obl like ? or raion like ? or sovet like ? or gni like ? or datel like ? or soaton like ? or datav like ?");
            
            for ( int i=1; i < 11; i++) stat.setString( i, "%" + searchValue + "%");
            ResultSet rs = stat.executeQuery();
            
            while (rs.next()) {
                City city = new City();
                     city.setId( rs.getInt("id") );
                     city.setSOATO( rs.getString("soato") );
                     city.setTIP( rs.getString("tip") );
                     city.setNAME( rs.getString( "name" ) );
                     city.setOBL( rs.getString("obl") );
                     city.setRAION( rs.getString("raion") );
                     city.setSOVET( rs.getString("sovet") );
                     city.setGNI( rs.getString("gni") );
                     city.setDATEL( rs.getString("datel") );   
                     city.setSOATON( rs.getString("soaton") );   
                     city.setDATAV( rs.getString("datav") );   

                    cities.add( city );
            }
            
        } catch ( SQLException e ){
            e.printStackTrace( System.err );
        } finally {
            if ( null != stat ) try{ stat.close(); } catch ( SQLException ignore ){}
            if ( null != conn ) try{ conn.close(); } catch ( SQLException ignore ){}
        }
        
            return cities;
    }
    
    
    
}

