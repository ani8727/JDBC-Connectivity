import java.sql.*;

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

            String query = "Select * from students";
//          statement.executeQuery(query);

            ResultSet resultset = statement.executeQuery(query);

            while(resultset.next()){
                int id=resultset.getInt("id");
                String name = resultset.getString("name");
                int age = resultset.getInt("age");
                double marks =resultset.getDouble("marks");
                System.out.println("ID: "+id);
                System.out.println("Name: "+name);
                System.out.println("Age: "+age);
                System.out.println("Marks: "+marks);
            }


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
