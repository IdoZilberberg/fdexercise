package com.ido.fdexercise.controller;

import com.ido.fdexercise.beans.Farm;
import com.ido.fdexercise.dto.FarmStatsDTO;
import com.ido.fdexercise.service.FarmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * User: ido
 */
@Controller
@RequestMapping("/farms")
public class FarmController {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


  @Resource
  private FarmService farmService;

  @RequestMapping(value = "/stats", method = RequestMethod.GET)
  public @ResponseBody FarmStatsDTO getFarmStats(@RequestParam("zipcode") final Integer zipcode, @RequestParam("seedingDate") final String seedingDateStr) {
    final LocalDate seedingDate = LocalDate.parse(seedingDateStr, formatter);
    final FarmStatsDTO response = farmService.getFarmStats(zipcode, seedingDate);

    return response;
  }

  @RequestMapping("/layout")
  public String getFarmPartialPage(ModelMap modelMap) {
    return "farms/layout";
  }
}
