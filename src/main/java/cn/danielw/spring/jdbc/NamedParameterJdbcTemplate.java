package cn.danielw.spring.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class NamedParameterJdbcTemplate extends org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate {

    public NamedParameterJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public NamedParameterJdbcTemplate(JdbcOperations classicJdbcTemplate) {
        super(classicJdbcTemplate);
    }

    @Override
    public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) {
        List<T> list = getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource), rowMapper);
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public <T> T queryForObject(String sql, Map<String, ?> paramMap, RowMapper<T>rowMapper) {
        return queryForObject(sql, new MapSqlParameterSource(paramMap), rowMapper);
    }

    public long queryForLong(String sql, Map<String, ?> params) throws DataAccessException {
        Long result = queryForObject(sql, params, Long.class);
        return result == null ? 0L : result;
    }

    public int queryForInt(String sql, Map<String, ?> params) throws DataAccessException {
        Integer result = queryForObject(sql, params, Integer.class);
        return result == null ? 0 : result;
    }

    public String queryForString(String sql, Map<String, ?> params) throws DataAccessException {
        return queryForObject(sql, params, String.class);
    }

    public boolean queryForBoolean(String sql, Map<String, ?> params) throws DataAccessException {
        Boolean result = queryForObject(sql, params, Boolean.class);
        return result == null ? false : result;
    }

}
