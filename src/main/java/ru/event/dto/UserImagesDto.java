package ru.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.event.entity.UserImages;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserImagesDto {

  private Long id;
  private String avatar;

  private UserImagesDto(Long id, String avatar) {
    this.id = id;
    this.avatar = avatar;
  }

  public static UserImagesDto userImagesDto(UserImages userImages) {
    return new UserImagesDto(userImages.getId(), userImages.getAvatar());
  }
}
