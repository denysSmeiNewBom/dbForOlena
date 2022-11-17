package dao;

import java.util.List;
import java.util.Optional;
import models.User;

public class UserDaoImpl implements UserDao{
    @Override
    public Optional<User> getUserById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public Optional<User> deleteUserById(long id) {
        return Optional.empty();
    }

    @Override
    public void updateUser(User user) {

    }
}
