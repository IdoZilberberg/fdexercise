package com.ido.fdexercise.beans;

import java.time.LocalDate;

/**
 * User: ido
 */
public class Farm {
  private Integer zipCode;
  private LocalDate seedingDate;

  public Farm() {
  }

  public Farm(final Integer zipCode, final LocalDate seedingDate) {
    this.zipCode = zipCode;
    this.seedingDate = seedingDate;
  }

  public Integer getZipCode() {
    return zipCode;
  }

  public void setZipCode(final Integer zipCode) {
    this.zipCode = zipCode;
  }

  public LocalDate getSeedingDate() {
    return seedingDate;
  }

  public void setSeedingDate(final LocalDate seedingDate) {
    this.seedingDate = seedingDate;
  }
}
