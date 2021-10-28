package ru.event.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonResponse extends LinkedHashMap<Object, Object> {

  public JsonResponse() {
  }

  public JsonResponse(Object key, Object value) {
    this.put(key, value);
  }

  public JsonResponse(Map<?, ?> map) {
    super(map);
  }

  public JsonResponse add(Object key, Object value) {
    this.put(key, value);
    return this;
  }
}
