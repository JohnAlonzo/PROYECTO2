package modelo;

import  java.awt.HeadlessException ;
import  java.sql.Connection ;
import  java.sql.PreparedStatement ;
import  java.sql.ResultSet ;
import  java.sql.SQLException ;
import  javax.swing.JOptionPane ;

public class Usuario {
    public int  login ( String user, String password ) {
        
        Connection connection =  null ;
        PreparedStatement pst;
        ResultSet rs;
        int State =  - 1 ;
        try { 
            connection = ConectionSQL.getInstance().getConnection();            
            if (connection!=null) {                
                String sql =  " SELECT * FROM usuarios WHERE BINARY user =? AND pass = AES_ENCRYPT (?, 'key') " ;                
                pst = connection.prepareStatement(sql);
                pst.setString( 1 , user);
                pst.setString( 2 , password);
                
                rs = pst.executeQuery();
                
                if(rs.next()) {
                    State =  1 ;
                }else{
                    State =  0 ;
                }   
            } else{
                JOptionPane . showMessageDialog ( null , " Hubo un error al conectarse con la base de datos " , " ERROR " , JOptionPane . ERROR_MESSAGE );
            }
        } catch ( HeadlessException  |  SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error de ejecucion, 'posibles errores: \n'"
            + ex.getMessage());
        } finally{
            try {
                if (connection!= null ) {
                    ConectionSQL.getInstance().closeConnection(connection);            
                }            
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());           
            }
        }  
        return State;   
    }  
}
