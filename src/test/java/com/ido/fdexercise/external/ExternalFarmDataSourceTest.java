package com.ido.fdexercise.external;

import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ido on 11/4/2015.
 */
public class ExternalFarmDataSourceTest {


  @Test
  public void testXmlMapper() throws Exception {

    XPath xPath = XPathFactory.newInstance().newXPath();
    final XPathExpression xpathTempMax = xPath.compile("/locations/location/day/temp_max");
    final XPathExpression xpathTempMin = xPath.compile("/locations/location/day/temp_min");
    final XPathExpression xpathPrecipIn = xPath.compile("/locations/location/day/precip_in");
    final XPathExpression xpathLat = xPath.compile("/locations/location/@request_lat");
    final XPathExpression xpathLng = xPath.compile("/locations/location/@request_lon");


    DocumentBuilderFactory builderFactory =
        DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = builderFactory.newDocumentBuilder();
    Document xmlDocument = builder.parse(getClass().getResourceAsStream("/weather-sample.xml"));


    String tempMax = xpathTempMax.evaluate(xmlDocument);
    String tempMin = xpathTempMin.evaluate(xmlDocument);
    String precipIn = xpathPrecipIn.evaluate(xmlDocument);
    String lat = xpathLat.evaluate(xmlDocument);
    String lng = xpathLng.evaluate(xmlDocument);

    assertEquals("76", tempMax);
    assertEquals("49", tempMin);
    assertEquals("1.23", precipIn);
    assertEquals("37.4067", lat);
    assertEquals("-83.9364", lng);

  }

}