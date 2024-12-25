package repository.custom.impl;

import dto.OrderDetails;
import entity.ItemEntity;
import entity.OrderDetailsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import repository.custom.OrderDetailsDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetailsEntity orderDetailsEntity) {
        try {
            String SQL = "INSERT INTO orderdetail VALUES(?,?,?)";
            return CrudUtil.execute(
                    SQL,
                    orderDetailsEntity.getOrder_Id(),
                    orderDetailsEntity.getItem_Id(),
                    orderDetailsEntity.getQty()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String SQL = "Delete FROM orderdetail WHERE order_id = "+id+";";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<OrderDetailsEntity> getAll() {
        ObservableList<OrderDetailsEntity> orderDetailsEntities = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM orderdetails;";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                orderDetailsEntities.add(new OrderDetailsEntity(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3)
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return orderDetailsEntities;
    }

    @Override
    public boolean update(OrderDetailsEntity orderDetailsEntity) {
        try {
            String SQL = "UPDATE orderdetail SET order_id=?,item_id=?,qtyTY=? WHERE order_id = "+ orderDetailsEntity.getOrder_Id()+";";
            return CrudUtil.execute(
                    SQL,
                    orderDetailsEntity.getOrder_Id(),
                    orderDetailsEntity.getItem_Id(),
                    orderDetailsEntity.getQty()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public OrderDetailsEntity search(String id) {
        OrderDetailsEntity orderDetailsEntity = null;
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM orderdetail WHERE order_id = "+id);
            resultSet.next();
            orderDetailsEntity = new OrderDetailsEntity(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
        return orderDetailsEntity;
    }
}
