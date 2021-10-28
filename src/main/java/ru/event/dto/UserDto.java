package ru.event.dto;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.event.entity.User;
import ru.event.entity.UserImages;

import javax.websocket.server.ServerEndpoint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
  private List<UserImagesDto> avatars;

  public static UserDto userDtoMapper(User user) {
    return new UserDto(user.getUsername(), user.getPhone(), user.getAvatars()
      .stream()
      .map(UserImagesDto::userImagesDto)
      .collect(Collectors.toList()));
  }
}
