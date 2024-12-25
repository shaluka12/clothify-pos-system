package repository.custom.impl;

import dto.OrderDetails;
import dto.Orders;
import entity.ItemEntity;
import entity.OrderDetailsEntity;
import entity.OrdersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import repository.custom.OrdersDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public boolean save(OrdersEntity ordersEntity) {
        try {
            String SQL = "INSERT INTO orders VALUES(?,?,?)";
            return CrudUtil.execute(
                    SQL,
                    ordersEntity.getId(),
                    ordersEntity.getDate(),
                    ordersEntity.getCost()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String SQL = "Delete FROM orders WHERE order_id = "+id+";";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<OrdersEntity> getAll() {
        ObservableList<OrdersEntity> ordersEntities = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM orders;";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                ordersEntities.add(new OrdersEntity(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getDouble(3)
                ));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return ordersEntities;
    }

    @Override
    public boolean update(OrdersEntity ordersEntity) {
        try {
            String SQL = "UPDATE orders SET date=?,cost=? WHERE order_id = "+ ordersEntity.getId()+";";
            return CrudUtil.execute(
                    SQL,
                    ordersEntity.getId(),
                    ordersEntity.getDate(),
                    ordersEntity.getCost()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public OrdersEntity search(String id) {
        OrdersEntity ordersEntity = null;
        ObservableList<OrderDetailsEntity> orderDetailsEntities = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM orders WHERE order_id = "+id);
            resultSet.next();
            ordersEntity = new OrdersEntity(
                    resultSet.getInt(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getDouble(3)
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
        return ordersEntity;
    }

    public boolean placeOrder(OrdersEntity ordersEntity, ObservableList<OrderDetailsEntity> orderDetailsEntities){
        System.out.println(ordersEntity+""+orderDetailsEntities);
        return false;
    }
}
