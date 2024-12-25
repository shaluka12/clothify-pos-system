package service.custom.impl;

import dto.OrderDetails;
import javafx.collections.ObservableList;
import repository.custom.OrderDetailsDao;
import service.custom.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Override
    public boolean addOrderDetails(OrderDetails orderDetails) {
        return false;
    }

    @Override
    public boolean deleteOrderDetails(String id) {
        return false;
    }

    @Override
    public ObservableList<OrderDetails> getAll() {
        return null;
    }

    @Override
    public boolean updateOrderDetails(OrderDetails orderDetails) {
        return false;
    }

    @Override
    public OrderDetails searchOrderDetails(String id) {
        return null;
    }

    @Override
    public ObservableList<String> getOrderDetailsIds() {
        return null;
    }
}
