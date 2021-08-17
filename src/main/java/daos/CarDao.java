package daos;

import models.Car;

import java.util.List;

public interface CarDao {
    List<Car> getCar();
    boolean insertCar(Car c);
    boolean updateCar(Car c);
    boolean deleteCar(Car c);
}
