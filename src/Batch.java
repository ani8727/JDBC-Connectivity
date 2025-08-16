import java.sql.*;
import java.util.Scanner;

public class Batch {

    private static final String url = "jdbc:mysql://localhost:3306/mydb";

    private  static final String username = "root";

    private static final String password = "@ani.8727M";

    public static void main(String[] args) {
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");

         } catch (ClassNotFoundException e){
             System.out.println(e.getMessage());
         }
         try {
             Connection connection= DriverManager.getConnection(url, username, password);
//             Statement statement = connection.createStatement();

             String query = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
             PreparedStatement preparedStatement = connection.prepareStatement(query);

             Scanner sc = new Scanner(System.in);

             while(true) {
                 System.out.print("Enter name: ");
                 String name = sc.next();
                 System.out.print("Enter age: ");
                 int age = sc.nextInt();
                 System.out.print("Enter marks: ");
                 double marks = sc.nextDouble();
                 System.out.print("Enter more data(Y/N): ");
                 String choice = sc.next();

//                 String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)", name, age, marks);
//                 statement.addBatch(query);

                 preparedStatement.setString(1,name);
                 preparedStatement.setInt(2,age);
                 preparedStatement.setDouble(3,marks);

                 preparedStatement.addBatch();

                 if (choice.equalsIgnoreCase("N")) {
                     break;
                 }
             }
            int[] arr= preparedStatement.executeBatch();

             for(int i=0;i<arr.length;i++){
                 if(arr[i]==0){
                     System.out.println("Query: "+i+" not executed Successfully!");
                 }
             }

         } catch (SQLException e){
             System.out.println(e.getMessage());
         }
    }
}
