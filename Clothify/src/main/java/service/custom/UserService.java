package service.custom;

import dto.User;
import javafx.collections.ObservableList;
import repository.CrudDao;
import service.SuperService;

public interface UserService extends SuperService {
    boolean addUser(User user);
    boolean deleteUser(String id);
    ObservableList<User> getAll();
    boolean updateUser(User user);
    User searchUser(String id);
    ObservableList<String> getUserIds();
}
