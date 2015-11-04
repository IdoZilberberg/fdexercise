package com.ido.fdexercise.dao;

import com.ido.fdexercise.model.FarmDailyStat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by Ido on 11/4/2015.
 */
public class FarmDailyStatMapper implements RowMapper<FarmDailyStat> {

  public FarmDailyStat mapRow(ResultSet rs, int rowNum) throws SQLException {

    FarmDailyStat result = new FarmDailyStat(
        rs.getInt("zipcode"),
        rs.getDate("seedingDate").toLocalDate(),
        rs.getDouble("precipIn"),
        rs.getDouble("tempMin"),
        rs.getDouble("tempMax"));

    return result;
  }
}
