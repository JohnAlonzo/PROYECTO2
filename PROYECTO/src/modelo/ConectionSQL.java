package modelo;

import java.sql.*;
import java.sql.Connection ;
import java.sql.SQLException ;
import org.apache.commons.dbcp2.BasicDataSource;


public class ConectionSQL {
    
    private final String DB = " persona " ;
    private final String URL = " jdbc: mysql: // localhost: 3306 / " + DB + " ?"
            + "useUnicode = true y useJDBCCompliantTimezoneShift = true y"
            + "useLegacyDatetimeCode = false y serverTimezone = UTC " ;
    private final String USER = " root " ; 
    private final String PASS = " " ;
    
    private static ConectionSQL dataSource ;
    private BasicDataSource basicDataSource = null ;
    
    private  ConectionSQL () {
     
        basicDataSource =  new  BasicDataSource ();
        basicDataSource . setDriverClassName ( " com.mysql.cj.jdbc.Driver " );
        basicDataSource . setUsername ( USER );
        basicDataSource . setPassword ( PASS );
        basicDataSource . setUrl ( URL );
        
        basicDataSource . setMinIdle ( 5 );
        basicDataSource . setMaxIdle ( 20 );
        basicDataSource . setMaxTotal ( 50 );
        basicDataSource . setMaxWaitMillis ( - 1 );   
    }
    public static ConectionSQL getInstance () { 
        if (dataSource ==  null ) {
            dataSource =  new  ConectionSQL ();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public void closeConnection(Connection connection) throws SQLException{
        
        connection.close();
    }

    Connection getConnection(Connection M) throws SQLException{
       if(M!=null){
				M.close();
				System.out.println("connection closed");
			}
    }
}
