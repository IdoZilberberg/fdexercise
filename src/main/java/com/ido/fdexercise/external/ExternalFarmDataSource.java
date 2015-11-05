package com.ido.fdexercise.external;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ido.fdexercise.model.FarmDailyStat;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Ido on 11/4/2015.
 */
public class ExternalFarmDataSource {

  private static final Logger log = LoggerFactory.getLogger(ExternalFarmDataSource.class);

  @Resource
  private DateTimeFormatter dateFormatter;

  @Resource
  private XmlMapper xmlMapper;

  private DocumentBuilder builder;
  XPathExpression xpathTempMax;
  XPathExpression xpathTempMin;
  XPathExpression xpathPrecipIn;
  XPathExpression xpathLat;
  XPathExpression xpathLng;

  public void init() throws XPathExpressionException, ParserConfigurationException {
    XPath xPath = XPathFactory.newInstance().newXPath();
    xpathTempMax = xPath.compile("/locations/location/day/temp_max");
    xpathTempMin = xPath.compile("/locations/location/day/temp_min");
    xpathPrecipIn = xPath.compile("/locations/location/day/precip_in");
    xpathLat = xPath.compile("/locations/location/@request_lat");
    xpathLng = xPath.compile("/locations/location/@request_lon");
    DocumentBuilderFactory builderFactory =
        DocumentBuilderFactory.newInstance();
    builder = builderFactory.newDocumentBuilder();
  }

  public List<FarmDailyStat> getForDates(Integer zipcode, Set<LocalDate> missingDates) {
    log.info("Now calling external source for {} missing dates.", missingDates.size());

    final List<FarmDailyStat> res = new ArrayList<>();
    for (final LocalDate date : missingDates) {
      try {
        res.add(getForDate(zipcode, date));
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
    return res;
  }

  private FarmDailyStat getForDate(Integer zipcode, LocalDate date) throws Exception {

    String resolvedUrl = String.format("http://weather.wdtinc.com/feeds/demo20150831/almanac.php?ZIP=%d&DATE=%s",
        zipcode, dateFormatter.format(date));
    URL url = new URL(resolvedUrl);
    String input = IOUtils.toString(url, "UTF-8");
    Document xmlDocument = builder.parse(new InputSource(new ByteArrayInputStream(input.getBytes("utf-8"))));
    String precipIn = xpathPrecipIn.evaluate(xmlDocument);
    String tempMin = xpathTempMin.evaluate(xmlDocument);
    String tempMax = xpathTempMax.evaluate(xmlDocument);
    String lat = xpathLat.evaluate(xmlDocument);
    String lng = xpathLng.evaluate(xmlDocument);

    log.info("Read from remote: zipcode={}, date={}, precipIn={}, tempMin={}, tempMax={}, lat={}, lng={}",
        zipcode, dateFormatter.format(date), precipIn, tempMin, tempMax, lat, lng);

    return new FarmDailyStat(zipcode, date, Double.parseDouble(precipIn), Double.parseDouble(tempMin), Double.parseDouble(tempMax),
        Double.parseDouble(lat), Double.parseDouble(lng));
  }

  public void setXmlMapper(final XmlMapper xmlMapper) {
    this.xmlMapper = xmlMapper;
  }
}
