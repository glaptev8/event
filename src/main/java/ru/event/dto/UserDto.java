package ru.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.event.entity.Event;
import ru.event.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
  private String username;
  private String phone;
  private List<Long> createEvent;
  private List<Long> allEvent;
  private List<UserImagesDto> avatars;

  public static UserDto userDtoMapper(User user) {
    return new UserDto(
      user.getUsername(),
      user.getPhone(),
      user.getCreateEvent()
        .stream()
        .map(Event::getId)
        .collect(Collectors.toList()),
      user.getAllEvent()
        .stream()
        .map(Event::getId)
        .collect(Collectors.toList()),
      user.getAvatars()
        .stream()
        .map(UserImagesDto::userImagesDto)
        .collect(Collectors.toList())
      );
  }
}
