package com.ido.fdexercise.dto;

/**
 * Ido
 */
public class FarmStatsDTO {

  private Double precip;
  private Double tempMin, tempMax;
  private Double lat, lng;

  public FarmStatsDTO() {
  }

  public FarmStatsDTO(Double precip, Double tempMin, Double tempMax, Double lat, Double lng) {
    this.precip = precip;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
    this.lat = lat;
    this.lng = lng;
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

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  @Override
  public String toString() {
    return "FarmStatsDTO{" +
        "precip=" + precip +
        ", tempMin=" + tempMin +
        ", tempMax=" + tempMax +
        ", lat=" + lat +
        ", lng=" + lng +
        '}';
  }
}
