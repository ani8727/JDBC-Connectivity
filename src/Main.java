public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
/*
 Flow of connect JDBC
 1. Connect IDE with a necessary Connector - jar file in project structure

 2. Create Connection - driver laod
 */
