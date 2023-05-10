package DBTest;

//coming from the sql dependency

import java.sql.*;

public class s1DataBaseTest {
    // to build the connection with the database
    // we need 3 things: url, username, password

    public static void main(String[] args) {
        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        //java database connection :(the database im trying to connect with is) mysql :// [ip address and port #] / [database name]
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        //we need to establish the connection to the database
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created for batch 15");

            //create a statement to send sql queries
            Statement statement = conn.createStatement();

            //when we send any query to the database then bd returns result set (table with rows and columns)
            //so we store the query result in an object of type ResultSet
            ResultSet rset = statement.executeQuery("select FirstName, LastName from person");

            rset.next();

            String fName = rset.getString("FirstName");
            String lName = rset.getString("LastName");
            System.out.println(fName + " " + lName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
