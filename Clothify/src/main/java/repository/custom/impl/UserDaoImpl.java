package repository.custom.impl;

import db.DBConnection;
import dto.User;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import repository.custom.UserDao;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserEntity userEntity) {
        try {
            String SQL = "INSERT INTO user VALUES(?,?,?,?,?)";
            return CrudUtil.execute(
                    SQL,
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getEmail(),
                    userEntity.getPassword(),
                    userEntity.getIsAdmin()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String SQL = "Delete FROM user WHERE user_id = "+id+";";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<UserEntity> getAll() {
        ObservableList<UserEntity> userEntityObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM user;";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                userEntityObservableList.add(new UserEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5)
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return userEntityObservableList;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        try {
            String SQL = "UPDATE user SET name=?,email=?,password=?,isAdmin=? WHERE user_id = "+ userEntity.getId()+";";
            return CrudUtil.execute(
                    SQL,
                    userEntity.getName(),
                    userEntity.getEmail(),
                    userEntity.getPassword(),
                    userEntity.getIsAdmin()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public UserEntity search(String email) {
        UserEntity User = null;
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE email = '"+email+"'");
            resultSet.next();
            User = new UserEntity(
                    resultSet.getInt("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("isAdmin")
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
        return User;
    }
}
