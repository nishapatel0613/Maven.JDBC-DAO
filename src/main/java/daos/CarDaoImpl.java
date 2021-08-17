package daos;

import Config.DatabaseConfig;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class CarDaoImpl implements CarDao{

    @Override
    public List<Car> getCar() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Connection connection = databaseConfig.getConnection("mysql");

        List<Car> carList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("USE jdbcdaolab");
            ResultSet rs = statement.executeQuery("SELECT * FROM Car");
            if (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setColor(rs.getString("color"));
                car.setVin(rs.getString("vin"));
                System.out.println(car.getId());
                System.out.println(car.getModel());
                System.out.println(car.getYear());
                System.out.println(car.getColor());
                System.out.println(car.getVin());

                carList.add(car);
            }
            return carList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertCar(Car c) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Connection connection = databaseConfig.getConnection("mysql");
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Car VALUES (NULL, ?, ?, ?)");
            ps.setInt(1, c.getId());
            ps.setString(2, c.getModel());
            ps.setInt(3, c.getYear());
            ps.setString(4, c.getColor());
            //ps.setString(5,c.getVin());
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateCar(Car c) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Connection connection = databaseConfig.getConnection("mysql");
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Car SET name=?, pass=?, age=? WHERE id=?");
            ps.setInt(1, c.getId());
            ps.setString(2, c.getModel());
            ps.setInt(3, c.getYear());
            ps.setString(4, c.getColor());
            ps.setString(5,c.getVin());
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCar(Car c) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Connection connection = databaseConfig.getConnection("mysql");
        try {
            Statement stmt = connection.createStatement();
            String id = null;
            int i = stmt.executeUpdate("DELETE FROM Car WHERE id=" + id);

            if (i == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public abstract boolean insertCar();
}
