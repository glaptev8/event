package ru.event.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.event.dto.Dictionary;
import ru.event.dto.EntertainmentType;
import ru.event.dto.JsonResponse;
import ru.event.dto.PrintType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DictionaryController {

  @GetMapping("/v1/api/get/dictionaries")
  public JsonResponse getAllDictionaries() {
    JsonResponse jsonResponse =  new JsonResponse()
      .add("PrintType", Arrays.stream(PrintType.values())
        .map(e -> new Dictionary(getEnumCode(e), e.getDescription()))
        .collect(Collectors.toList()))
      .add("EntertainmentType", Arrays.stream(EntertainmentType.values())
        .map(e -> new Dictionary(getEnumCode(e), e.getDescription()))
        .collect(Collectors.toList()));
    System.out.println(jsonResponse);
    return jsonResponse;
  }

  private String getEnumCode(Enum<?> e) {
    return e.name();
  }

}
