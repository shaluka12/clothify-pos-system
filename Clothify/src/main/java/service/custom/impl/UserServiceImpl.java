package service.custom.impl;

import dto.User;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import repository.DaoFactory;
import repository.custom.UserDao;
import repository.custom.impl.UserDaoImpl;
import service.custom.UserService;
import util.DaoType;

public class UserServiceImpl implements UserService {

    public UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);

    @Override
    public boolean addUser(User user) {
        return userDao.save(new ModelMapper().map(user, UserEntity.class));
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.delete(id);
    }

    @Override
    public ObservableList<User> getAll() {
        ObservableList<UserEntity> userEntities = userDao.getAll();
        ObservableList<User> users = FXCollections.observableArrayList();
        userEntities.forEach(userEntity -> users.add(new ModelMapper().map(userEntity,User.class)));

        return users;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.update(new ModelMapper().map(user, UserEntity.class));
    }

    @Override
    public User searchUser(String email) {
        return new ModelMapper().map(userDao.search(email), User.class);
    }

    @Override
    public ObservableList<String> getUserIds() {
        return null;
    }
}
