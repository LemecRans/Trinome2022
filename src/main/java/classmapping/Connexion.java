package classmapping;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	public static Connection con() {
        java.sql.Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProjetMobile","root","");          
        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}


