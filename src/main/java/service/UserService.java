package service;

import java.util.List;
import models.User;

public interface UserService {
    User getUserById(long id);

    List<User> getAllUser();

    User deleteUserById(long id);

    void  updateUser(User user);
}
