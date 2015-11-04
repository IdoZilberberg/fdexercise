package com.ido.fdexercise.model;

import java.time.LocalDate;

/**
 * Ido
 *
 * Record to reflect a single line in the DB
 */
public class FarmDailyStat implements Comparable<FarmDailyStat>{

  private Integer zipcode;
  private LocalDate date;
  private Double precipIn, tempMin, tempMax;

  public FarmDailyStat() {
  }

  public FarmDailyStat(Integer zipcode, LocalDate date, Double precipIn, Double tempMin, Double tempMax) {
    this.zipcode = zipcode;
    this.date = date;
    this.precipIn = precipIn;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
  }

  public int compareTo(FarmDailyStat o) {
    Integer zipCompare = this.zipcode.compareTo(o.getZipcode());
    if(zipCompare != 0 ) {
      return zipCompare;
    }

    Integer dateCompare = this.date.compareTo(o.getDate());
    return dateCompare;
  }

  public Integer getZipcode() {
    return zipcode;
  }

  public void setZipcode(Integer zipcode) {
    this.zipcode = zipcode;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Double getPrecipIn() {
    return precipIn;
  }

  public void setPrecipIn(Double precipIn) {
    this.precipIn = precipIn;
  }

  public Double getTempMin() {
    return tempMin;
  }

  public void setTempMin(Double tempMin) {
    this.tempMin = tempMin;
  }

  public Double getTempMax() {
    return tempMax;
  }

  public void setTempMax(Double tempMax) {
    this.tempMax = tempMax;
  }
}
