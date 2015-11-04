package com.ido.fdexercise.dao;

import com.ido.fdexercise.model.FarmDailyStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ido
 *
 * DB impl in memory
 */
public class FarmStatsDAOImplMem implements FarmStatsDAO {

  private static final Logger log = LoggerFactory.getLogger(FarmStatsDAOImplMem.class);

  private JdbcTemplate jdbcTemplate;

  @Resource
  private DateTimeFormatter dateFormatter;


  public FarmStatsDAOImplMem(final DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public void setDateFormatter(DateTimeFormatter dateFormatter) {
    this.dateFormatter = dateFormatter;
  }

  public void insertFarmDailyStat(final FarmDailyStat farmDailyStat) {
    jdbcTemplate.update("insert into stats values(?, PARSEDATETIME(?, 'yyyy-MM-dd'), ?, ?, ?)",
        farmDailyStat.getZipcode(), dateFormatter.format(farmDailyStat.getDate()),
        farmDailyStat.getPrecipIn(), farmDailyStat.getTempMin(), farmDailyStat.getTempMax());
    log.debug("Inserted line to DB. ZipCode={}, SeedingDate={}", farmDailyStat.getZipcode(), dateFormatter.format(farmDailyStat.getDate()));
  }

  public List<FarmDailyStat> getFarmDailyStats(Integer zipcode, LocalDate startDate, LocalDate endDate)
  {
    final String startDateStr = dateFormatter.format(startDate);
    final String endDateStr = dateFormatter.format(endDate);
    List<FarmDailyStat> res = jdbcTemplate.query("select * from stats where zipcode = ? and seedingDate between ? and ? order by zipcode, seedingDate", new Object[] { zipcode, startDateStr, endDateStr}, new FarmDailyStatMapper());
//    FarmDailyStat stat = new FarmDailyStat(1234, LocalDate.of(2015, 12, 31), 0.01, 10.1, 20.2);
//    List<FarmDailyStat> res = new ArrayList<FarmDailyStat>();
//    res.add(stat);
    log.debug("Read {} lines from DB", res.size());
    return res;
  }

  @Override
  public void insertFarmDailyStats(final List<FarmDailyStat> stats) {
    stats.forEach(this::insertFarmDailyStat);
  }


}
