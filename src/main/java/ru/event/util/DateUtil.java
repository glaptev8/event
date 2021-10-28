package ru.event.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Date;

@Getter
@Setter
public final class DateUtil {
  public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  public static Date parseDate(String s) {
    return parse(s, DATE_FORMAT);
  }

  public static Date parse(String text, DateTimeFormatter formatter) {
    if (StringUtils.isEmpty(text)) return null;

    TemporalAccessor temporal;
    try {
      temporal = formatter.parse(text.trim());
    } catch (DateTimeParseException e) {
      return null;
    }

    LocalDate date = temporal.query(TemporalQueries.localDate());
    if (date == null) return  null;

    LocalTime time = temporal.query(TemporalQueries.localTime());
    if (time == null) {
      time = LocalTime.of(0, 0, 0, 0);
    }

    return toDate(LocalDateTime.of(date, time));
  }

  public static Date toDate(LocalDateTime dateTime) {
    if (dateTime == null) return null;
    return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
  }
}
