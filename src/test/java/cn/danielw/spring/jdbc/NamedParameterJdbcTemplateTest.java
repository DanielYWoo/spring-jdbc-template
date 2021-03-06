package cn.danielw.spring.jdbc;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class NamedParameterJdbcTemplateTest {

    private static NamedParameterJdbcTemplate test;
    private static final RowMapper<String> RM = (rs, rowNum) -> rs.getString("c1") + ":" + rs.getString("c2");

    private Map<String, Object> params(String c1, Object v1) {
        Map<String, Object> result = new HashMap<>();
        result.put(c1, v1);
        return result;
    }

    @BeforeClass
    public static void init() throws SQLException {
        test = new NamedParameterJdbcTemplate(TestUtil.getDS());
    }

    @Test
    public void testQueryForBoolean() throws SQLException, ClassNotFoundException {
        assertFalse(test.queryForBoolean("select c2 from test where c1=:c1", params("c1", 0)));
        assertTrue(test.queryForBoolean("select c2 from test where c1=:c1", params("c1", 1)));
    }

    @Test
    public void testQueryForInt() throws SQLException, ClassNotFoundException {
        assertEquals(0, test.queryForInt("select c2 from test where c1 =:c1", params("c1", 0)));
        assertEquals(1, test.queryForInt("select c2 from test where c1 =:c1", params("c1", 1)));
    }

    @Test
    public void testQueryForLong() throws SQLException, ClassNotFoundException {
        assertEquals(0L, test.queryForLong("select c2 from test where c1 =:c1", params("c1", 0)));
        assertEquals(1L, test.queryForLong("select c2 from test where c1 =:c1", params("c1", 1)));
    }

    @Test
    public void testQueryForObject() throws SQLException, ClassNotFoundException {
        assertNull(test.queryForObject("select * from test where c1 = :c1", params("c1", 0), RM));
        assertEquals("1:1", test.queryForObject("select * from test where c1 = :c1", params("c1", 1), RM));
    }

}
