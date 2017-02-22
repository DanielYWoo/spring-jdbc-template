[![Build Status](https://travis-ci.org/DanielYWoo/spring-jdbc-template.svg?branch=master)](https://travis-ci.org/DanielYWoo/spring-jdbc-template)

# spring-jdbc-template
Spring jdbc template throws EmptyResultDataAccessException when no result is found,
but 99.99% of developers expect NULL, right? This is a very simple wrapper for
JdbcTemplate and NamedParameterJdbcTemplate, but returns null for queryForObject() methods.

# usage
Exactly like Spring JdbcTemplate, actually it's a subclass of it.

e.g.
```java
 int value = new JdbcTemplate(dataSource).queryForInt("select age from user where id=?", id);
 User u = new NamedParameterJdbcTemplate(dataSource).queryForObject(
    "select * from user where id=:id", params, rowMapper);
```
