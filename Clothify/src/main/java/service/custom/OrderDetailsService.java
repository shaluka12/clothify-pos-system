package service.custom;

import dto.OrderDetails;
import javafx.collections.ObservableList;
import repository.CrudDao;
import service.SuperService;

public interface OrderDetailsService extends SuperService {
    boolean addOrderDetails(OrderDetails orderDetails);
    boolean deleteOrderDetails(String id);
    ObservableList<OrderDetails> getAll();
    boolean updateOrderDetails(OrderDetails orderDetails);
    OrderDetails searchOrderDetails(String id);
    ObservableList<String> getOrderDetailsIds();
}
