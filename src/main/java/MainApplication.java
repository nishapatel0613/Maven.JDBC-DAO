
import Config.DatabaseConfig;
import com.mysql.cj.jdbc.Driver;
import daos.CarDaoImpl;

import java.sql.*;

public class MainApplication {
    public static void main(String[] args) {

        CarDaoImpl carDao = new CarDaoImpl() {
            @Override
            public boolean insertCar() {
                return false;
            }
        };
        carDao.getCar();
    }


}
