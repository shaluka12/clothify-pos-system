package repository.custom.impl;

import dto.Supplier;
import entity.SupplierEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import repository.custom.SupplierDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        try {
            String SQL = "INSERT INTO supplier VALUES(?,?,?,?)";
            return CrudUtil.execute(
                    SQL,
                    supplierEntity.getId(),
                    supplierEntity.getName(),
                    supplierEntity.getCompany(),
                    supplierEntity.getEmail()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String SQL = "Delete FROM supplier WHERE supplier_id = "+id+";";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        ObservableList<SupplierEntity> supplierEntityObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM supplier;";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                supplierEntityObservableList.add(new SupplierEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return supplierEntityObservableList;
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        try {
            String SQL = "UPDATE supplier SET name=?,company=?,email=? WHERE supplier_id = "+ supplierEntity.getId()+";";
            return CrudUtil.execute(
                    SQL,
                    supplierEntity.getName(),
                    supplierEntity.getCompany(),
                    supplierEntity.getEmail()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public SupplierEntity search(String id) {
        SupplierEntity supplierEntity = null;
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier WHERE supplier_id = "+id+";");
            resultSet.next();
            supplierEntity = new SupplierEntity(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
        return supplierEntity;
    }
}
