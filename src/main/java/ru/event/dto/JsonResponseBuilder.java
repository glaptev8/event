package ru.event.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.event.util.ApplicationContextProvider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JsonResponseBuilder {
  private final Map<Object, Object> map;

  public JsonResponseBuilder() {
    this.map = new LinkedHashMap<>();
  }

  public JsonResponseBuilder(Object key, Object value) {
    this.map = new LinkedHashMap<>();
    add(key, value);
  }

  public JsonResponseBuilder add(Object key, Object value) {
    if (value instanceof Iterable) {
      Iterable<?> iterable = (Iterable<?>) value;
      map.put(key,
        StreamSupport.stream(iterable.spliterator(), false)
          .map(this::convert)
          .collect(Collectors.toList())
      );
    } else {
      map.put(key, convert(value));
    }
    return this;
  }

  public JsonSerializer build() {
    return new JsonSerializer(map);
  }

  private Object convert(Object value) {
    if (isPrimitive(value)) {
      return value;
    }
    return ApplicationContextProvider.getApplicationContext().getBean(ObjectMapper.class).convertValue(value, Map.class);
  }

  private boolean isPrimitive(Object val) {
    return val == null || val instanceof CharSequence || val instanceof Number;
  }

}
