package Modulo;

import java.sql.*;

public class Conexao {
    
    public static Connection conector(){
        
        Connection conexao = null;
        
        String driver = "com.mysql.jdbc.Driver";
     
        try {
            Class.forName(driver);
            
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/edm", "root", "");
            return conexao;
        } catch (Exception e) {
        
            return null;
        }
    
    }
}
