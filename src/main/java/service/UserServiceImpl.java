package service;

import dao.UserDao;
import dao.UserDaoImpl;
import java.util.List;
import models.User;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDaoImpl();

    @Override
    public User getUserById(long id) {
        return dao.getUserById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> getAllUser() {
        return dao.getAllUser();
    }

    @Override
    public boolean deleteUserById(long id) {
        return dao.deleteUserById(id);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
