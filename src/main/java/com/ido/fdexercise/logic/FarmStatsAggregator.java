package com.ido.fdexercise.logic;

import com.ido.fdexercise.dto.FarmStatsDTO;
import com.ido.fdexercise.model.FarmDailyStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Ido on 11/4/2015.
 */
public class FarmStatsAggregator {

  private static final Logger log = LoggerFactory.getLogger(FarmStatsAggregator.class);

  public FarmStatsDTO aggregateStats(final List<FarmDailyStat> farmDailyStats) {
    log.info("Aggregating data for {} days.", farmDailyStats.size());

    Double precipTotal=0.0, lowestTemp=Double.MAX_VALUE, highestTemp=Double.MIN_VALUE;

    for( final FarmDailyStat stat : farmDailyStats )  {
      precipTotal += stat.getPrecipIn();
      lowestTemp = stat.getTempMin()<lowestTemp?stat.getTempMin():lowestTemp;
      highestTemp = stat.getTempMax()>highestTemp?stat.getTempMax():highestTemp;
    }
    return new FarmStatsDTO(precipTotal, lowestTemp, highestTemp);
  }


}
