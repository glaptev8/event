package ru.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.event.entity.Event;
import ru.event.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class EventDto {
  private Long id;

  private String description;

  private Date startDate;

  private Date endDate;

  private Integer countUser;

  private String latitude;

  private String longitude;

  private EntertainmentType entertainmentType;

  private Long creater;

  private List<Long> users = new ArrayList<>();

  public static EventDto eventDtoMapper(Event event) {
    return new EventDto(event.getId(),
      event.getDescription(),
      event.getStartDate(),
      event.getEndDate(),
      event.getCountUser(),
      event.getLatitude(),
      event.getLongitude(),
      event.getEntertainmentType(),
      event.getCreater().getId(),
      event.getUsers()
        .stream()
        .map(User::getId)
        .collect(Collectors.toList())
    );
  }
}
