package dev.ducku.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void addProducts(String name) {
        String sql = "insert into products values (NULL, ?)";
        jdbcTemplate.update(sql, name);
        throw new RuntimeException(":(");
    }
}
