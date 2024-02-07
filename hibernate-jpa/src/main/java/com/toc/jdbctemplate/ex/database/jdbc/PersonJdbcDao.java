package com.toc.jdbctemplate.ex.database.jdbc;

import com.toc.jdbctemplate.ex.database.entity.Person;
import com.toc.jdbctemplate.ex.database.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public List<Person> findById(int id) {
        return jdbcTemplate.query("select * from person where id = ?",
                new PersonMapper(), id);
    }

    public List<Person> findByName(String name) {

        return jdbcTemplate.query("select * from person where name = ?",
                new PersonMapper(), name);
    }

    public List<Person> findByLocation(String location) {
        return jdbcTemplate.query("select * from person where location = ?", new PersonMapper(), location);
    }

    public int deleteByIdAndName(int id, String name) {
        return jdbcTemplate.update("delete from person where id = ? and name = ?", id, name);
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from person where id = ?", id);
    }

    public int insertIntoTable(Person person) {
        Object[] params = new Object[]{person.getId(), person.getName(), person.getLocation(), person.getBithDate()};
        return jdbcTemplate.update("insert into person (id, name, location, birth_date) values (?, ?, ?, ?)", params);
    }

    public int updateTable(String location, int id) {
        Object[] params = new Object[]{location, id};
        return jdbcTemplate.update("update person set location = ? where id = ?", params);
    }

}
