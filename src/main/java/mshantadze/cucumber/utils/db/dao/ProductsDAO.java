package mshantadze.cucumber.utils.db.dao;

import mshantadze.cucumber.utils.db.connection.ConnectionFactory;
import mshantadze.cucumber.utils.db.mappers.ProductMapper;
import mshantadze.cucumber.utils.db.models.Product;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

public class ProductsDAO {
    public static List<Product> getProducts(long userId) {
        ProductMapper productMapper;
        try (SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession(true)){
            productMapper = sqlSession.getMapper(ProductMapper.class);
            return productMapper.getUserProducts(userId);
        }
    }

    public static BigDecimal getProductsTotalPrice(long userId) {
        ProductMapper productMapper;
        try (SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession(true)){
            productMapper = sqlSession.getMapper(ProductMapper.class);
            return productMapper.getProductsTotalPrice(userId);
        }
    }
}
