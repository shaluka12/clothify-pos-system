package repository.custom;

import dto.OrderDetails;
import dto.Orders;
import entity.OrderDetailsEntity;
import entity.OrdersEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface OrdersDao extends CrudDao<OrdersEntity> {
    boolean placeOrder(OrdersEntity ordersEntity, ObservableList<OrderDetailsEntity> orderDetailsEntities);
}
