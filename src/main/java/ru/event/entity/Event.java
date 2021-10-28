package ru.event.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.event.dto.EntertainmentType;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties
public class Event {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User creater;

  @ManyToMany(mappedBy = "allEvent", fetch = FetchType.EAGER)
  private Set<User> users = new LinkedHashSet<>();

  @Enumerated(EnumType.STRING)
  private EntertainmentType entertainmentType;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Moscow")
  private Date startDate;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Moscow")
  private Date endDate;

  private Integer countUser;

  private String latitude;

  private String longitude;
}
