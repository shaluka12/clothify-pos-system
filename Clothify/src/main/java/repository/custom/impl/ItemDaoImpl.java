package repository.custom.impl;

import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import repository.custom.ItemDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity itemEntity) {
        try {
            String SQL = "INSERT INTO item VALUES(?,?,?,?,?,?)";
            return CrudUtil.execute(
                    SQL,
                    itemEntity.getId(),
                    itemEntity.getName(),
                    itemEntity.getSize(),
                    itemEntity.getPrice(),
                    itemEntity.getQty(),
                    itemEntity.getSupplier_Id()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String SQL = "Delete FROM item WHERE item_id = "+id+";";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<ItemEntity> getAll() {
        ObservableList<ItemEntity> itemEntityObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM item;";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                itemEntityObservableList.add(new ItemEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return itemEntityObservableList;
    }

    @Override
    public boolean update(ItemEntity itemEntity) {
        try {
            String SQL = "UPDATE item SET name=?,size=?,price=?,Qty=?,supplier_id=? WHERE item_id = "+ itemEntity.getId()+";";
            return CrudUtil.execute(
                    SQL,
                    itemEntity.getName(),
                    itemEntity.getSize(),
                    itemEntity.getPrice(),
                    itemEntity.getQty(),
                    itemEntity.getSupplier_Id()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ItemEntity search(String id) {
        ItemEntity itemEntity = null;
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM item WHERE item_id = "+id);
            resultSet.next();
            itemEntity = new ItemEntity(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6)
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return itemEntity;
    }
}
