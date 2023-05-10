package DBTest;

import java.sql.*;

public class s1DatabaseTest2 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created for batch 15");

            Statement statement = conn.createStatement();

            ResultSet rset = statement.executeQuery(
                    "select firstname, lastname, age, city " +
                            "from person " +
                            "where city is not null;");

//            while(rset.next()) {
//                String fName = rset.getString("firstname");
//                String lName = rset.getString("lastname");
//
//                System.out.println(fName + " " + lName);
//
//            }

            // ResultSetMetaData - object that contains info about the ResultSet
            // info like amount of columns, name of columns/rows, # of rows

            ResultSetMetaData metaData = rset.getMetaData();

            //print all the column header values
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                System.out.println(columnName);
            }
            System.out.println();
            
            while (rset.next()) {
                //moving from 1 row to another row, we use while loop

                //moving from column to column, we use for loop
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String value = rset.getString(metaData.getColumnName(i));

                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
