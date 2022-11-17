package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import models.User;

public interface UserDao {
    User create(User manufacturer) throws SQLException;

    Optional<User> getUserById(long id);

    List<User> getAllUser();

    boolean deleteUserById(long id);

    User updateUser(User user);
}
