package cn.danielw.spring.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplate extends org.springframework.jdbc.core.JdbcTemplate {

    public JdbcTemplate() {
        super();
    }

    public JdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public JdbcTemplate(DataSource dataSource, boolean lazyInit) {
        super(dataSource, lazyInit);
    }

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper) {
        List<T> list = query(sql, rowMapper);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object ... args) {
        List<T> list = query(sql, rowMapper, args);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {
        List<T> list = query(sql, getSingleColumnRowMapper(requiredType), args);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public long queryForLong(String sql, Object... args) throws DataAccessException {
        Long result = queryForObject(sql, Long.class, args);
        return result == null ? 0L : result;
    }

    public int queryForInt(String sql, Object... args) throws DataAccessException {
        Integer result = queryForObject(sql, Integer.class, args);
        return result == null ? 0 : result;
    }

    public String queryForString(String sql, Object... args) throws DataAccessException {
        return queryForObject(sql, String.class, args);
    }

    public boolean queryForBoolean(String sql, Object... args) throws DataAccessException {
        Boolean result = queryForObject(sql, Boolean.class, args);
        return result == null ? false : result;
    }

}
