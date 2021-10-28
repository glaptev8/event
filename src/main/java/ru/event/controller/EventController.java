package ru.event.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.event.dto.FilterEvent;
import ru.event.dto.JsonSerializer;
import ru.event.dto.PrintType;
import ru.event.dto.UserDto;
import ru.event.entity.Event;
import ru.event.entity.User;
import ru.event.entity.UserImages;
import ru.event.repositroy.EventRepository;
import ru.event.repositroy.UserImagesRepository;
import ru.event.repositroy.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class EventController {

  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private UserImagesRepository userImagesRepository;

  @PostMapping("/event/get/by/filter")
  public JsonSerializer getEventByFilter(@RequestBody FilterEvent filterEvent, @RequestBody PrintType printType) {
    List<User> listEvent = eventRepository.getListEvent(filterEvent.getPageSize() == 0 ? 1 : filterEvent.getPageSize(),
      filterEvent.getPageNumber(),
      filterEvent.getStartDate(),
      filterEvent.getEndDate(),
      filterEvent.getEntertainmentType());
    return new JsonSerializer("result", listEvent);
  }

  @PostMapping("/event/create")
  public JsonSerializer createEvent(@RequestBody JsonSerializer jsonEvent, @AuthenticationPrincipal User user) throws JsonProcessingException {
    Event event = objectMapper.convertValue(jsonEvent.get("event"), Event.class);
    event.setCreater(user);
    event.getUsers().add(user);
    Event eventSave = eventRepository.save(event);
    user.getAllEvent().add(eventSave);
    user.getCreateEvent().add(eventSave);
    userRepository.save(user);
    return new JsonSerializer("result", "success");
  }

  @PostMapping("/api/v1/profile")
  public void update(@RequestBody UserDto user, @AuthenticationPrincipal User authUser) {
    if (StringUtils.hasLength(user.getUsername())) {
      authUser.setUsername(user.getUsername());
    }
    if (StringUtils.hasLength(user.getPhone())) {
      authUser.setPhone(user.getPhone());
    }
    Set<UserImages> userImages = new HashSet<>();
    user.getAvatars().forEach(a -> {
      UserImages userImage = new UserImages();
      userImage.setAvatar(a.getAvatar());
      userImage.setCreater(authUser);
      userImages.add(userImagesRepository.save(userImage));
    });
    userImages.addAll(authUser.getAvatars());
    authUser.setAvatars(userImages);
    userRepository.save(authUser);
  }

  @PostMapping("/api/v1/profile/image/delete")
  public void deleteImageById(@RequestBody Long id) {
    userImagesRepository.findById(id).ifPresent(userImages -> userImagesRepository.delete(userImages));
  }

  @PostMapping("/v1/api/event/create")
  public void createEvent(@RequestBody Event event, @AuthenticationPrincipal User user) {
    if (event != null && user != null) {
      event.setCreater(user);
      eventRepository.save(event);
    }
  }

}
