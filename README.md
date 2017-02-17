[![Build Status](https://travis-ci.org/DanielYWoo/spring-jdbc-template.svg?branch=master)](https://travis-ci.org/DanielYWoo/spring-jdbc-template)

# spring-jdbc-template
Spring jdbc template throws EmptyResultDataAccessException when no result is found,
but 99.99% of developers expect NULL, right? This is a very simple wrapper for
JdbcTemplate and NamedParameterJdbcTemplate, but returns null for queryForObject() methods.


