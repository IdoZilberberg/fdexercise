package com.ido.fdexercise.service;

import com.ido.fdexercise.dto.FarmStatsDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * User: ido
 */
@Service("farmService")
public class FarmServiceImpl implements FarmService {

  @Override
  public FarmStatsDTO getFarmStats(Integer zipcode, LocalDate seedingDate) {
    return new FarmStatsDTO(123.456, 2.1, 3.4);
  }

}
