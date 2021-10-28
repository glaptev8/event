package ru.event.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.event.util.DateUtil;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FilterEvent {
  private int pageSize;
  private int pageNumber;
  private Date startDate;
  private Date endDate;
  private List<EntertainmentType> entertainmentType;
}
