package com.example.gameoflife;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


public class DataBase {
    public static int x;
    public static int y;
    public static String foodname;
    public static String type;
    public static String name;
    public static int steps;
    public static Connection getConnection() {
       try{
           String driver = "com.mysql.cj.jdbc.Driver";
           String url = "jdbc:mysql://localhost:3306/test";
           String username = "root";
           String password = "Speedy09@";
           Class.forName(driver);

           Connection connection = DriverManager.getConnection(url,username,password);
           System.out.println("Connected");

           return connection;
       }catch (Exception e){
           System.out.println(e);
       }
        return null;
    }

    public static void refreshTable(String name) throws Exception {
        DataBase.name = name;
        Connection connection = getConnection();
        assert connection != null;
        try (PreparedStatement refresh = connection.prepareStatement("delete from test." + name + " where ID > 0 and ID <9999")) {
            refresh.executeUpdate();
        }
    }

    public static void getAllCell(String name) throws SQLException, IOException {
        DataBase.name = name;
        Connection connection = getConnection();
        Statement statement;
        assert connection != null;
        statement = connection.createStatement();
        String state = "Select *From" + " " + name;
        String csvFilePath = "src/main/java/com/example/gameoflife/" + "Export" + name + ".csv";
        ResultSet resultSet = statement.executeQuery(state);
        FileWriter fw = new FileWriter(csvFilePath);
        fw.write("X,Y,TYPE\n");
        while (resultSet.next()) {
            String type = resultSet.getString("CELL_TYPE");
            int x = resultSet.getInt("CELL_X_COORDINATE");
            int y = resultSet.getInt("CELL_Y_COORDINATE");
            fw.write(x + "," + y + "," + type);
            fw.write("\n");
        }
        fw.close();
        connection.close();
    }

    public static void createTable(String name) {
        try {
            DataBase.name = name;
            Connection connection = getConnection();
            assert connection != null;
            PreparedStatement create = connection.prepareStatement(
                    "CREATE TABLE " + name + "(ID INT NOT NULL AUTO_INCREMENT,CELL_X_COORDINATE INT,CELL_Y_COORDINATE INT,CELL_TYPE VARCHAR(255))"
            );
            create.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Function Complete");
        }
    }

    public static void removedeadcell(int x, int y, String name) {
        DataBase.x = x;
        DataBase.y = y;
        DataBase.name = name;

        try {
            Connection connection = getConnection();
            assert connection != null;
            PreparedStatement deletecell = connection.prepareStatement("DELETE FROM" + name + " WHERE CELL_X_COORDINATE=" + x + "AND CELL_Y_COORDINATE=" + y);
            deletecell.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enter(int x, int y, String type, String name) {
        DataBase.x = x;
        DataBase.y = y;
        DataBase.type = type;
        DataBase.name = name;
        try {
            Connection connection = getConnection();
            assert connection != null;
            PreparedStatement posted = connection.prepareStatement("INSERT INTO " + name + "(CELL_X_COORDINATE,CELL_Y_COORDINATE,CELL_TYPE)VALUES('" + x + "','" + y + "','" + type + "')");
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Insert Completed");
        }
    }

    public static void refreshID(String name) throws SQLException {
        DataBase.name = name;
        Connection connection = getConnection();
        assert connection != null;
        PreparedStatement refresh = connection.prepareStatement("ALTER TABLE " + name + " auto_increment = 0");
        refresh.executeUpdate();
    }

    public static void enter(int i, int i1, String name) {
    }
}
