package com.ido.fdexercise.beans;

import java.time.LocalDate;

/**
 * User: ido
 */
public class Farm {
  private Integer zipcode;
  private LocalDate seedingDate;

  public Farm() {
  }

  public Farm(final Integer zipcode, final LocalDate seedingDate) {
    this.zipcode = zipcode;
    this.seedingDate = seedingDate;
  }

  public Integer getZipcode() {
    return zipcode;
  }

  public void setZipcode(final Integer zipcode) {
    this.zipcode = zipcode;
  }

  public LocalDate getSeedingDate() {
    return seedingDate;
  }

  public void setSeedingDate(final LocalDate seedingDate) {
    this.seedingDate = seedingDate;
  }
}
