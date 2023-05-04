package mshantadze.cucumber.utils.db.dao;

import mshantadze.cucumber.utils.db.connection.ConnectionFactory;
import mshantadze.cucumber.utils.db.mappers.UserMapper;
import mshantadze.cucumber.utils.db.models.User;

import java.util.List;

public class UsersDAO {
    public static User getUser(String username) {
        UserMapper userMapper = ConnectionFactory
                .getSqlSessionFactory()
                .openSession(true)
                .getMapper(UserMapper.class);

        return userMapper.getUser(username);
    }
}
