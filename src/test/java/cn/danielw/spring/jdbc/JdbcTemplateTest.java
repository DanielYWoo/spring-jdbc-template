package cn.danielw.spring.jdbc;

import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class JdbcTemplateTest {

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        EmbeddedDatabaseFactory fac = new EmbeddedDatabaseFactory();
        fac.setDatabaseType(EmbeddedDatabaseType.H2);
        DataSource ds = fac.getDatabase();
        ds.getConnection().createStatement().execute("create table test (c1 int, c2 int)");

        JdbcTemplate test = new JdbcTemplate(ds);
        assertFalse(test.queryForBoolean("select c2 from test"));

    }

}
