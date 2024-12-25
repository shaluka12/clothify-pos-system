package service.custom;

import dto.OrderDetails;
import dto.Orders;
import javafx.collections.ObservableList;
import service.SuperService;

public interface OrdersService extends SuperService {
    boolean addOrders(Orders orders);
    boolean deleteOrders(String id);
    ObservableList<Orders> getAll();
    boolean updateOrders(Orders orders);
    Orders searchOrders(String id);
    ObservableList<String> getOrdersIds();
    boolean placeOrder(Orders orders, ObservableList<OrderDetails> orderDetails);
}
