package com.ido.fdexercise.dao;

import com.ido.fdexercise.model.FarmDailyStat;

import java.time.LocalDate;
import java.util.List;

/**
 * Ido
 */
public interface FarmStatsDAO {

  void insertFarmDailyStat(final FarmDailyStat farmDailyStat);

  List<FarmDailyStat> getfarmDailyStats(final Integer zipcode, final LocalDate startDate, final LocalDate endDate);

}
