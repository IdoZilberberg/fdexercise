package com.ido.fdexercise.dao;

import com.ido.fdexercise.model.FarmDailyStat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Ido
 *
 * DB impl in memory
 */
public class FarmStatsDAOImplMem implements FarmStatsDAO {

  private Map<Integer, List<FarmDailyStat>> memoryDB;



  @Override
  public void insertFarmDailyStat(FarmDailyStat farmDailyStat) {

  }

  @Override
  public List<FarmDailyStat> getfarmDailyStats(Integer zipcode, LocalDate startDate, LocalDate endDate) {
    return null;
  }
}
