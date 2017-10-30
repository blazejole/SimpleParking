package pl.blazej.dao;


import pl.blazej.UserParking;
import pl.blazej.connection.Connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImplements implements iDao {
    private Connector connector = Connector.getInstance();
    public boolean addCar(UserParking car) {

        PreparedStatement preparedStatement
                = connector.getNewPreparedStatement("INSERT INTO parking VALUES(?,?,?)");
        try {
            preparedStatement.setString(1,"0");
            preparedStatement.setInt(2,car.getWhichPlace());
            preparedStatement.setString(3,"1");
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean removeCar(int whichPlace) {
        PreparedStatement preparedStatement =
                connector.getNewPreparedStatement("DELETE FROM parking WHERE miejsce = ?");
        try {
            preparedStatement.setInt(1, whichPlace);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  List<Integer> showAll() {
        List<Integer> users = new ArrayList<>();
        PreparedStatement preparedStatement =
                connector.getNewPreparedStatement("SELECT * FROM parking;");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                users.add(resultSet.getInt("miejsce"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean removeAllCars() {
        PreparedStatement preparedStatement =
                connector.getNewPreparedStatement("DELETE FROM parking;");
        try {
           preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeCarsInParking(int newPlace, int oldPlace) {
        PreparedStatement preparedStatement =
                connector.getNewPreparedStatement("UPDATE parking SET miejsce=? WHERE miejsce =?;");
        try {
            preparedStatement.setInt(1, newPlace);
            preparedStatement.setInt(2,oldPlace);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
