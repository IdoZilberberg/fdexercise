package com.ido.fdexsercise.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.junit.Assert.*;

/**
 * Created by Ido on 11/4/2015.
 */
public class FarmStatsDAOImplMemTest {

  private EmbeddedDatabase db;

  @Before
  public void setUp() {
    // creates a HSQL in-memory db populated from default scripts classpath:schema.sql and classpath:test-data.sql
    db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
  }

  @Ignore
  @Test
  public void testDataAccess() {
    JdbcTemplate template = new JdbcTemplate(db);
    Integer count = template.queryForObject("select count(*) from stats", Integer.class);
    assertEquals(Integer.valueOf(2), count);
  }

  @After
  public void tearDown() {
    db.shutdown();
  }

}