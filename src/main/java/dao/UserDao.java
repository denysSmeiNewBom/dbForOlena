package dao;

import java.util.List;
import java.util.Optional;
import models.User;

public interface UserDao {
    Optional<User> getUserById(long id);

    List<User> getAllUser();

    Optional<User> deleteUserById(long id);

    void  updateUser(User user);
}
