package com.ido.fdexercise.external;

import com.ido.fdexercise.model.FarmDailyStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Ido on 11/4/2015.
 */
public class ExternalFarmDataSource {

  private static final Logger log = LoggerFactory.getLogger(ExternalFarmDataSource.class);

  public List<FarmDailyStat> getForDates(Integer zipcode, Set<LocalDate> missingDates) {
    log.info("Now calling external source for {} missing dates.", missingDates.size());

    return new ArrayList<>();

  }
}
