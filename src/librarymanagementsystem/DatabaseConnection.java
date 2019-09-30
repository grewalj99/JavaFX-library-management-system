/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystem;

import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageBase;
import com.sun.corba.se.spi.ior.iiop.GIOPVersion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author grewal
 */
public class DatabaseConnection {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        createConnection();
    }
    
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        
            Class.forName("com.mysql.jdbc.Driver");
          //  System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection ("jdbc:mysql://localhost/librarydb", "root", "Janmeet46");
          //  System.out.println("Database connected");
           
            return connection;
    }
    
}
