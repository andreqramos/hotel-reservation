package service;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class Database {

    public Connection connection;
    public Statement statement;
    public ResultSet result;
    public PreparedStatement pst;

    Dotenv dotenv = Dotenv.load();
    final String user = dotenv.get("DATABASE_USER");;
    final String password = dotenv.get("DATABASE_PASSWORD");
    final String database = "HotelReservationDB";

    final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private boolean check = false;

    public void connect(){
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection made sucefully: " + connection);
        }catch (SQLException e){
            System.out.println("Connection Error: " + e.getMessage());
        }
    }
}
