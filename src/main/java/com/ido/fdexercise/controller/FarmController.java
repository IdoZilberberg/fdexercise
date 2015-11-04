package com.ido.fdexercise.controller;

import com.ido.fdexercise.dto.FarmStatsDTO;
import com.ido.fdexercise.service.FarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * User: ido
 */
@Controller
@RequestMapping("/farms")
public class FarmController {

  private static final Logger log = LoggerFactory.getLogger(FarmController.class);

  @Resource
  private DateTimeFormatter dateFormatter;

  @Resource
  private FarmService farmService;


  @RequestMapping(value = "/stats", method = RequestMethod.GET)
  public
  @ResponseBody
  FarmStatsDTO getFarmStats(@RequestParam("zipcode") final Integer zipcode, @RequestParam("seedingDate") final String seedingDateStr) {
    log.info("Called /stats with ZipCode={} and SeedingDate={}", zipcode, seedingDateStr);
    final LocalDate seedingDate = LocalDate.parse(seedingDateStr, dateFormatter);
    final FarmStatsDTO response = farmService.getFarmStats(zipcode, seedingDate);

    return response;
  }

  @RequestMapping("/layout")
  public String getFarmPartialPage(ModelMap modelMap) {
    return "farms/layout";
  }

  public void setDateFormatter(DateTimeFormatter dateFormatter) {
    this.dateFormatter = dateFormatter;
  }
}
