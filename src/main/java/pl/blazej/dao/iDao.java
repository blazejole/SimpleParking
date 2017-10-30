package pl.blazej.dao;

import pl.blazej.UserParking;

import java.util.List;

public interface iDao {
    boolean addCar(UserParking car);
    boolean removeCar(int whichPlace);
    List<Integer> showAll();
    boolean removeAllCars();
    boolean changeCarsInParking(int newPlace, int oldPlace);

}
