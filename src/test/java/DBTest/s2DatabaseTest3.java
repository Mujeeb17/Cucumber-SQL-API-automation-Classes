package DBTest;


import utils.CommonMethods;
import utils.ConfigReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class s2DatabaseTest3 extends CommonMethods {
    public static void main(String[] args) {


        ConfigReader.readProperties();
        String url = ConfigReader.getPropertyValue("urldb");
        String username = ConfigReader.getPropertyValue("usernamedb");
        String password = ConfigReader.getPropertyValue("passworddb");

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created for batch 15");

            //create a statement to send sql queries
            Statement statement = conn.createStatement();
            String query = "select * from person;";
            ResultSet rset = statement.executeQuery(query);
            ResultSetMetaData rsmdata = rset.getMetaData();

            //extract data from resultset and store it in java data structure

            List<Map<String, String>> listFromRset = new ArrayList<>();

            //iterate through the rows
            while (rset.next()){
                Map<String, String> map = new LinkedHashMap<>();
                //iterate over columns
                for (int i=1; i<=rsmdata.getColumnCount(); i++){
                    //fetching key and value from the columns
                    String key = rsmdata.getColumnName(i);
                    String value = rset.getString(key);
                    map.put(key, value);
                }
                System.out.println(map);
                listFromRset.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
