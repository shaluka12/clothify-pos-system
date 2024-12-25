package service.custom.impl;

import dto.Item;
import dto.OrderDetails;
import dto.Orders;
import entity.ItemEntity;
import entity.OrderDetailsEntity;
import entity.OrdersEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import repository.custom.OrdersDao;
import service.custom.OrdersService;
import util.DaoType;

public class OrdersServiceImpl implements OrdersService {

    public OrdersDao ordersDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);

    @Override
    public boolean addOrders(Orders orders) {
        return ordersDao.save(new ModelMapper().map(orders, OrdersEntity.class));
    }

    @Override
    public boolean deleteOrders(String id) {
        return ordersDao.delete(id);
    }

    @Override
    public ObservableList<Orders> getAll() {
        ObservableList<OrdersEntity> ordersEntities = ordersDao.getAll();
        ObservableList<Orders> orders = FXCollections.observableArrayList();
        ordersEntities.forEach(ordersEntity -> orders.add(new ModelMapper().map(ordersEntity,Orders.class)));

        return orders;
    }

    @Override
    public boolean updateOrders(Orders orders) {
        return ordersDao.update(new ModelMapper().map(orders, OrdersEntity.class));
    }

    @Override
    public Orders searchOrders(String id) {
        return new ModelMapper().map(ordersDao.search(id), Orders.class);
    }

    @Override
    public ObservableList<String> getOrdersIds() {
        return null;
    }

    @Override
    public boolean placeOrder(Orders orders, ObservableList<OrderDetails> orderDetails){
        ObservableList<OrderDetailsEntity> ordersEntities = FXCollections.observableArrayList();
        orderDetails.forEach(orderDetail -> ordersEntities.add(new ModelMapper().map(orderDetail, OrderDetailsEntity.class)));
        return ordersDao.placeOrder(new ModelMapper().map(orders, OrdersEntity.class),ordersEntities);
    }
}
