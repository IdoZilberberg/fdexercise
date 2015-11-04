package com.ido.fdexercise.service;

import com.ido.fdexercise.dao.FarmStatsDAO;
import com.ido.fdexercise.dto.FarmStatsDTO;
import com.ido.fdexercise.external.ExternalFarmDataSource;
import com.ido.fdexercise.logic.FarmStatsAggregator;
import com.ido.fdexercise.model.FarmDailyStat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * User: ido
 */
@Service("farmService")
public class FarmServiceImpl implements FarmService {

  @Resource
  private FarmStatsDAO dao;

  @Resource
  private FarmStatsAggregator farmStatsAggregator;

  @Resource
  private ExternalFarmDataSource externalFarmDataSource;

  public void setDao(FarmStatsDAO dao) {
    this.dao = dao;
  }

  public void setFarmStatsAggregator(FarmStatsAggregator farmStatsAggregator) {
    this.farmStatsAggregator = farmStatsAggregator;
  }

  public FarmStatsDTO getFarmStats(Integer zipcode, LocalDate seedingDate) {
//    return new FarmStatsDTO(123.456, 2.1, 3.4);

    final LocalDate yesterday = LocalDate.now().minusDays(1);

    if(seedingDate.compareTo(yesterday) >0 ) {
      throw new IllegalArgumentException("Seeding date cannot be today or in the future!");
    }

    final int totalDaysInRange = Period.between(seedingDate, yesterday).getDays() + 1;

    List<FarmDailyStat> farmDailyStats = dao.getFarmDailyStats(zipcode, seedingDate, yesterday);
    final List<FarmDailyStat> fullData = new ArrayList<>();
    fullData.addAll(farmDailyStats);
    if( totalDaysInRange > farmDailyStats.size() ) {

      final List<FarmDailyStat> missingData = getMissingDataBetweenDates(zipcode, seedingDate, yesterday, farmDailyStats);
      dao.insertFarmDailyStats(missingData);
      fullData.addAll(missingData);
    }

    final FarmStatsDTO farmStatsDTO = farmStatsAggregator.aggregateStats(fullData);

    return farmStatsDTO;

  }

  private List<FarmDailyStat> getMissingDataBetweenDates(final Integer zipcode, final LocalDate startDate, final LocalDate endDate, final List<FarmDailyStat> farmDailyStats) {

    final int totalDaysInRange = (int)(ChronoUnit.DAYS.between(startDate, endDate) + 1);
    final Set<LocalDate> datesWithAvailableData = farmDailyStats
        .stream()
        .map(FarmDailyStat::getDate).collect(Collectors.toCollection(TreeSet::new));
    final Set<LocalDate> missingDates = IntStream
        .range(0,totalDaysInRange)
        .mapToObj(value -> startDate.plusDays(value))
        .filter(localDate -> !datesWithAvailableData.contains(localDate))
        .collect(Collectors.toCollection(TreeSet::new));

    final List<FarmDailyStat> dataFromMissingDates = externalFarmDataSource.getForDates(zipcode, missingDates);

    return dataFromMissingDates;
  }

}
