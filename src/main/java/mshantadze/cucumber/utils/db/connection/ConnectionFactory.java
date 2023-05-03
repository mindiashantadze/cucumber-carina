package mshantadze.cucumber.utils.db.connection;

import mshantadze.cucumber.utils.db.mappers.ProductMapper;
import mshantadze.cucumber.utils.db.mappers.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class ConnectionFactory {
    private static SqlSessionFactory connectionFactory;

    static {
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connectionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return connectionFactory;
    }

    public static UserMapper getUserMapper() {
        try(SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            return sqlSession.getMapper(UserMapper.class);
        }
    }

    public static ProductMapper getProductMapper() {
        try(SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession(true)) {
            return sqlSession.getMapper(ProductMapper.class);
        }
    }
}
