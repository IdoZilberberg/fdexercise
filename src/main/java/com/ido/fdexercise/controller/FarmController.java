package com.ido.fdexercise.controller;

import com.ido.fdexercise.beans.Farm;
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

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");


  @Resource
  private FarmService farmService;

//  @RequestMapping("trainslist.json")
//  public
//  @ResponseBody
//  List<Farm> getFarmList() {
//    return farmService.getAllFarms();
//  }

  @RequestMapping(value = "/stats", method = RequestMethod.POST)
  public
  @ResponseBody
  void getFarmStats(@RequestParam("zipcode") final Integer zipcode, @RequestParam("seedingDate") final String seedingDateStr) {
    final LocalDate seedingDate = LocalDate.parse(seedingDateStr, formatter);
    farmService.getFarmStats(zipcode, seedingDate);
  }

  @RequestMapping("/layout")
  public String getTrainPartialPage(ModelMap modelMap) {
    return "trains/layout";
  }
}
