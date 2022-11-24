package service;

import dao.UserDao;
import dao.UserDaoImpl;
import java.util.List;
import java.util.logging.Logger;
import models.User;

public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getLogger(String.valueOf(UserServiceImpl.class));

    UserDao dao = new UserDaoImpl();

    @Override
    public User getUserById(long id) {
        log.info("Executing select to DB with id: " + id);
        User user = dao.getUserById(id).orElseThrow(RuntimeException::new);
        log.info("Retrieve from DB User with id: " + user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        log.info("Executing select to DB");
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
