package cn.danielw.spring.jdbc;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TestUtil {

    private static SimpleDriverDataSource ds;

    public static synchronized DataSource getDS() throws SQLException {
        if (ds != null) {
            return ds;
        }
        ds = new SimpleDriverDataSource();
        ds.setDriverClass(org.h2.Driver.class);
        ds.setUrl("jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        ds.getConnection().createStatement().execute("create table test (c1 int, c2 int)");
        ds.getConnection().createStatement().execute("insert into test (c1, c2) values(1, 1)");
        return ds;
    }

}

