package ru.event.dto;

public enum EntertainmentType {
  RUN("run"),
  BIKE("bike"),
  LONG_BOARD("long board");

  private String description;

  EntertainmentType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
