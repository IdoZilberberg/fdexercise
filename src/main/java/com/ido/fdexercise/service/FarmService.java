package com.ido.fdexercise.service;

import com.ido.fdexercise.dto.FarmStatsDTO;

import java.time.LocalDate;

/**
 * User: ido
 */
public interface FarmService {
  FarmStatsDTO getFarmStats(final Integer zipcode, final LocalDate seedingDate);
}
