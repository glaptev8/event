package ru.event.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonSerializer extends LinkedHashMap<Object, Object> {
  public JsonSerializer() {
  }

  public JsonSerializer(Object key, Object value) {
    this.put(key, value);
  }

  public JsonSerializer(Map<?, ?> map) {
    super(map);
  }

  public JsonSerializer add(Object key, Object value) {
    this.put(key, value);
    return this;
  }
}

