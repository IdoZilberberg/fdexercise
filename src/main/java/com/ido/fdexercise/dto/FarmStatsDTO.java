package com.ido.fdexercise.dto;

/**
 * Ido
 */
public class FarmStatsDTO {

  private Double precip;
  private Double tempMin, tempMax;

  public FarmStatsDTO() {
  }

  public FarmStatsDTO(Double precip, Double tempMin, Double tempMax) {
    this.precip = precip;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
  }

  public Double getPrecip() {
    return precip;
  }

  public void setPrecip(Double precip) {
    this.precip = precip;
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
