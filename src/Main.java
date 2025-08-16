import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/?user=root";

    private static final String username = "root";

    private static final String password = "@ani.8727M";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection= DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
/*
 Flow of connect JDBC
 1. Connect IDE with a necessary Connector - jar file in project structure

 2. Load necessary Driver - Driver load

 3. Create Connection - three Components(url,username,password)


 */
