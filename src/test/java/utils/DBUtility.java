package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    //even if we don't put '= null' it will still be null when we declare it
    //some companies have standards that say we should always declare it as null;
    static Connection conn = null;
    static Statement statement = null;
    private static ResultSet rset;
    private static ResultSetMetaData rSetMetaData;

    //this method create Connection to DB, execute query, and return object for result set
    public static ResultSet getResultSet(String query) {
        try {
            conn = DriverManager.getConnection(ConfigReader.getPropertyValue("urldb"),
                    ConfigReader.getPropertyValue("usernamedb"),
                    ConfigReader.getPropertyValue("passworddb"));

            statement = conn.createStatement();
            rset = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rset;
    }

    //this method will return the object of rSetMetaData
    public static ResultSetMetaData getResultSetMetaData(String query) {

        //to get metaData we need to get ResultSet first, so we call this method
        rset = getResultSet(query);

        rSetMetaData = null;

        //we use this line to get the data in tabular format so that we can use
        //these in column keys & values for retrieval operations
        try {
            rSetMetaData = rset.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSetMetaData;
    }

    //this method is extracting the data which will be stored in list of maps
    public static List<Map<String, String>> getListOfMapsFromRset(String query) {

        rSetMetaData = getResultSetMetaData(query);

        List<Map<String, String>> listFromRset = new ArrayList<>();

        try {

            while (rset.next()) {

                Map<String, String> map = new LinkedHashMap<>();

                for (int i = 1; i <= rSetMetaData.getColumnCount(); i++) {
                    //fetching key and value from the columns
                    String key = rSetMetaData.getColumnName(i);
                    String value = rset.getString(key);
                    map.put(key, value);
                }
                System.out.println(map);
                listFromRset.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rset);
            closeStatement(statement);
            closeConnection(conn);
        }

        return listFromRset;
    }

    //order to make connection:
    //connection
    //statement
    //resultset

    //order to close the connection:
    //close resultset
    //close statement
    //close connection

    public static void closeResultSet(ResultSet rset) {
        if (rset != null) {
            try {
                rset.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
