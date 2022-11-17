package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.User;
import utils.ConnectionUtil;

public class UserDaoImpl implements UserDao{
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";

    @Override
    public User create(User user) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
            PreparedStatement createStatement =
                    connection.prepareStatement(
                            "INSERT INTO users(name,country) values(?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            createStatement.setString(1, user.getName());
            createStatement.setInt(2, user.getAge());
            createStatement.executeUpdate();
            ResultSet generatedKeys = createStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getObject(1, Long.class);
                user.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't evaluate  create() for  user: "
                    + user, e);
        } finally {
            connection.close();
        }
        return user;
    }

    @Override
    public Optional<User> getUserById(long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement getStatement =
                     connection.prepareStatement(
                             "SELECT * FROM users WHERE id=? AND is_deleted = FALSE")) {
            getStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = getStatement.executeQuery();
            if (resultSet.next()) {
                User users = getUsers(resultSet);
                return Optional.of(users);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't evaluate get() for id: "
                    + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUser() {
        ArrayList<User> usersArrayList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement getStatement =
                     connection.prepareStatement(
                             "SELECT * FROM users WHERE is_deleted = FALSE ")) {
            ResultSet resultSet = getStatement.executeQuery();
            while (resultSet.next()) {
                User users = getUsers(resultSet);
                usersArrayList.add(users);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't evaluate getALL() User", e);
        }
        return usersArrayList;
    }

    @Override
    public boolean deleteUserById(long id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement deleteStatement =
                     connection.prepareStatement(
                             "UPDATE users SET is_deleted = TRUE WHERE id=?")) {
            deleteStatement.setLong(1, id);
            return deleteStatement.executeUpdate() >= 1;
        } catch (SQLException e) {
            throw new RuntimeException("Can't evaluate  update()."
                    + " There is no element if DB with id: ", e);
        }
    }

    @Override
    public User updateUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement updateStatement =
                     connection.prepareStatement(
                             "UPDATE users SET name = ?, country = ? WHERE id=?")) {
            updateStatement.setString(1, user.getName());
            updateStatement.setInt(2, user.getAge());
            updateStatement.setLong(3, user.getId());
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Can't evaluate  update() for  user: "
                    + user, e);
        }
    }

    private static User getUsers(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getObject(ID, Long.class));
        user.setName(resultSet.getString(NAME));
        user.setAge(resultSet.getInt(AGE));
        return user;
    }
}
