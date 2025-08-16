import java.sql.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";

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

            // statement
//            Statement statement = connection.createStatement();

            // for retrieving data

//            String query = "Select * from students";
//            statement.executeQuery(query);

//            ResultSet resultset = statement.executeQuery(query);

//            while(resultset.next()){
//                int id=resultset.getInt("id");
//                String name = resultset.getString("name");
//                int age = resultset.getInt("age");
//                double marks =resultset.getDouble("marks");
//                System.out.println("ID: "+id);
//                System.out.println("Name: "+name);
//                System.out.println("Age: "+age);
//                System.out.println("Marks: "+marks);
//            }

//            for inserting data

//            String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)", "Aniket", 20, 90.5);
//            int rowsAffected = statement.executeUpdate(query);
//
//            if(rowsAffected>0){
//                System.out.println("Data Inserted Successfully");
//            }else{
//                System.out.println("Data not Inserted");
//            }

//            for updating data

//            String query = String.format("UPDATE students SET marks = %f WHERE id = %d", 89.5, 2);
//            int rowsAffected = statement.executeUpdate(query);
//
//            if(rowsAffected>0){
//                System.out.println("Data Update Successfully");
//            }else{
//                System.out.println("Data not Update");
//            }

//            for delete data

//            String query = "DELETE FROM students WHERE id =2";
//            int rowsAffected = statement.executeUpdate(query);
//
//            if(rowsAffected>0){
//                System.out.println("Data Delete Successfully");
//            }else{
//                System.out.println("Data not Delete");
//            }

            // prepared statement

            // for creation value
//            String query= "INSERT INTO students(name, age, marks) VALUE(?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1,"Aniket");
//            preparedStatement.setInt(2,20);
//            preparedStatement.setDouble(3,94.5);
//
//            int rowAffected = preparedStatement.executeUpdate();
//
//            if(rowAffected>0){
//                System.out.println("Inserted Successfully");
//            }else {
//                System.out.println("Not Inserted");
//            }

            // for retrieve value
//
//            String query = "SELECT * FROM students WHERE ID = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setInt(1,1);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                System.out.println("Id: "+resultSet.getInt("id"));
//                System.out.println("Name: "+resultSet.getString("name"));
//                System.out.println("marks: "+resultSet.getDouble("marks"));
//            }else{
//                System.out.println("not retrieve data");
//            }

            // for update value
//            String query= "UPDATE students SET marks = ? WHERE name = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setDouble(1,99.5);
//            preparedStatement.setString(2,"Aniket");
//
//            int rowAffected = preparedStatement.executeUpdate();
//
//            if(rowAffected>0){
//                System.out.println("Updated Successfully");
//            }else {
//                System.out.println("Not Updated");
//            }

            // for delete value
            String query= "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,3);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected>0){
                System.out.println("Delete Successfully");
            }else {
                System.out.println("Not Delete");
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
