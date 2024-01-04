package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databaseconnection { 

    private static Connection con=null;
    private static Databaseconnection db=null;

    private String url="jdbc:mysql://localhost:3306/revspeed";
    private String uname="root";

    private String pass="root";

    public Connection getCon() {
        return con;
    }

    private Databaseconnection() throws SQLException,ClassNotFoundException
    {
       // Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection(url,uname,pass);
       /* if(con!=null) {
            System.out.println("Database Connected Successfully");
        }
        else {
            System.out.println("Database Connection Error...........");
        }*/
    }

    public static Databaseconnection access() throws SQLException,ClassNotFoundException
    {
        if(con==null)
        {
            db=new Databaseconnection();
        }
        else if(db.getCon().isClosed())
        {
            db=new Databaseconnection();
        }
        return db;
    }

}
