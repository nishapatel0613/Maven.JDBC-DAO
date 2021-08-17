package Config;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;


import org.springframework.context.annotation.Bean;

public class DatabaseConfig {

    public Connection getMyDBConnection() {
        registerJDBCDriver();
        Connection mySqlConnection = getConnection("mysql");
        //executeStatement(mySqlConnection,"USE jdbcdaolab");
       // executeStatement(mySqlConnection,"CREATE DATABASE IF NOT EXISTS jdbcdaolab;");

        return mySqlConnection;
    }

    public static void executeStatement(Connection connection, String sqlStatement) {
        try{
            Statement statement = getScrollableStatement(connection);
            statement.execute(sqlStatement);

        }catch (SQLException e){
            throw new Error(e);
        }
    }

    static Statement getScrollableStatement(Connection connection){
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try{
            return connection.createStatement(resultSetType,resultSetConcurrency);
        }catch (SQLException e){
            throw new Error(e);
        }
    }

    public static Connection getConnection(String mysql) {
        String username = "root";
        String password = "zipcode0";
        String url = new StringBuilder()
                .append("jdbc:")
                .append(mysql)
                .append("://localhost:3306/")
                .append("?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
                .toString();
        try{
            return DriverManager.getConnection(url,username,password);
        }catch (SQLException e) {
            throw new Error(e);
        }
    }

    public static void registerJDBCDriver(){
        try{
            DriverManager.registerDriver(Driver.class.newInstance());
        }catch (InstantiationException | IllegalAccessException | SQLException e1){
            throw new RuntimeException(e1);

        }
    }


}
