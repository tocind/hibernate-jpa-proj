package com.toc.jdbctemplate.ex;

import com.toc.jdbctemplate.ex.database.entity.Person;
import com.toc.jdbctemplate.ex.database.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HibernateJpaApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PersonJdbcDao personJdbcDao;

    @Autowired
    public HibernateJpaApplication(PersonJdbcDao personJdbcDao) {
        this.personJdbcDao = personJdbcDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
		logger.info("all users -> {}", personJdbcDao.findAll());
        logger.info("-----------------------------------------");
        logger.info("one user by id -> {}", personJdbcDao.findById(10003));
        logger.info("-----------------------------------------");
        logger.info("one user by name -> {}", personJdbcDao.findByName("abhi"));
        logger.info("-----------------------------------------");
        logger.info("one user by location -> {}", personJdbcDao.findByLocation("bangalore"));
        logger.info("------------------delete by id and name-----------------------");
        logger.info("delete user by id and name -> {}", personJdbcDao.deleteByIdAndName(10004, "jack"));
        logger.info("------------------delete by id-----------------------");
        logger.info("delete user by id -> {}", personJdbcDao.deleteById(10004));
        logger.info("------------------insert a record-----------------------");
        logger.info("delete user by id -> {}", personJdbcDao.insertIntoTable(getPerson()));
        logger.info("------------------update table for 10001-----------------------");
        logger.info("delete user by id -> {}", personJdbcDao.updateTable("delhi", 10001));
    }

    private Person getPerson() {
        return new Person(10005, "mike", "dublin", new Date());
    }
}
